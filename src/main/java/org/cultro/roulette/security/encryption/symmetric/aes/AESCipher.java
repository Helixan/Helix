package org.cultro.roulette.security.encryption.symmetric.aes;

import org.cultro.roulette.security.encryption.EncryptionAlgorithm;
import org.cultro.roulette.security.encryption.symmetric.SymmetricCipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Implementation of the SymmetricCipher interface using the Advanced Encryption Standard (AES).
 * This class provides functionality for encrypting and decrypting data using AES with various modes of operation.
 * AES supports key sizes of 128, 192, or 256 bits.
 */
public class AESCipher implements SymmetricCipher {

    private final int keySize;
    private final AESEncryptionMode encryptionMode;
    private byte[] iv;


    /**
     * Constructs an AESCipher with a specified key size and encryption mode.
     *
     * @param keySize         Size of the AES key in bits. Must be either 128, 192, or 256.
     * @param encryptionMode  The mode of AES encryption (e.g., ECB, CBC, etc.).
     * @throws IllegalArgumentException If the key size is not one of the specified valid values.
     */
    public AESCipher(int keySize, AESEncryptionMode encryptionMode) {
        if (keySize != 128 && keySize != 192 && keySize != 256) {
            throw new IllegalArgumentException("Invalid AES key size. Allowed values are 128, 192, or 256 bits.");
        }
        this.keySize = keySize;
        this.encryptionMode = encryptionMode;
    }


    /**
     * Encrypts the given plaintext using the specified AES key.
     * The method of encryption is determined by the specified encryption mode.
     * For modes other than ECB, an Initialization Vector (IV) is used and generated randomly.
     *
     * @param plaintext The plaintext to encrypt.
     * @param key       The AES key to use for encryption.
     * @return The encrypted data, including the IV prepended if applicable.
     */
    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(encryptionMode.getMode());

            if (encryptionMode != AESEncryptionMode.AES_ECB_PKCS5PADDING) {
                iv = new byte[cipher.getBlockSize()];
                new SecureRandom().nextBytes(iv);
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            }

            byte[] encrypted = cipher.doFinal(plaintext);

            if (encryptionMode != AESEncryptionMode.AES_ECB_PKCS5PADDING) {
                byte[] combined = new byte[iv.length + encrypted.length];
                System.arraycopy(iv, 0, combined, 0, iv.length);
                System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
                return combined;
            }

            return encrypted;
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting with AES", e);
        }
    }


    /**
     * Decrypts the given ciphertext using the specified AES key.
     * The method of decryption is determined by the specified encryption mode.
     * For modes other than ECB, an Initialization Vector (IV) is extracted from the ciphertext.
     *
     * @param ciphertext The ciphertext to decrypt.
     * @param key        The AES key to use for decryption.
     * @return The decrypted plaintext.
     */
    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(encryptionMode.getMode());

            byte[] actualCiphertext = ciphertext;

            if (encryptionMode != AESEncryptionMode.AES_ECB_PKCS5PADDING) {
                iv = Arrays.copyOfRange(ciphertext, 0, cipher.getBlockSize());
                actualCiphertext = Arrays.copyOfRange(ciphertext, cipher.getBlockSize(), ciphertext.length);
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, keySpec);
            }

            return cipher.doFinal(actualCiphertext);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting with AES", e);
        }
    }


    /**
     * Generates a new AES key of the specified key size.
     *
     * @return The generated AES key.
     */
    @Override
    public byte[] generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(keySize);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating AES key", e);
        }
    }


    /**
     * Generates a new AES key based on the provided seed.
     * The seed is used to initialize a SecureRandom instance, ensuring reproducibility of the key.
     *
     * @param seed The seed used for key generation.
     * @return The generated AES key.
     */
    @Override
    public byte[] generateKey(byte[] seed) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            keyGenerator.init(keySize, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating AES key with seed", e);
        }
    }


    /**
     * Returns the encryption algorithm used, which is AES in this implementation.
     *
     * @return The encryption algorithm, AES.
     */
    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return EncryptionAlgorithm.AES;
    }
}