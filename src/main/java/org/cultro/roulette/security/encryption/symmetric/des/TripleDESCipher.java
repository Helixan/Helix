package org.cultro.roulette.security.encryption.symmetric.des;

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
 * Implementation of the SymmetricCipher interface for the Triple DES encryption algorithm.
 * Triple DES, or 3DES, is an extension of the DES algorithm, providing enhanced security
 * by encrypting data with three keys instead of one.
 * This class provides functionality for encrypting and decrypting data using the Triple DES algorithm
 * in CBC mode with PKCS5 padding.
 */
public class TripleDESCipher implements SymmetricCipher {

    private static final String TRIPLE_DES_ALGORITHM = "DESede";
    private static final String TRIPLE_DES_TRANSFORMATION = "DESede/CBC/PKCS5Padding";
    private byte[] iv;


    /**
     * Encrypts the given plaintext using the specified Triple DES key.
     * Encrypts the data using CBC mode with PKCS5 padding.
     * An Initialization Vector (IV) is generated randomly for each encryption operation.
     *
     * @param plaintext The plaintext to encrypt.
     * @param key       The Triple DES key to use for encryption.
     * @return The encrypted data, including the IV prepended to the actual ciphertext.
     */
    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, TRIPLE_DES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION);

            iv = new byte[cipher.getBlockSize()];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(plaintext);
            byte[] combined = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
            return combined;
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting with Triple DES", e);
        }
    }


    /**
     * Decrypts the given ciphertext using the specified Triple DES key.
     * Decrypts the data using CBC mode with PKCS5 padding.
     * The IV is extracted from the beginning of the ciphertext.
     *
     * @param ciphertext The ciphertext to decrypt.
     * @param key        The Triple DES key to use for decryption.
     * @return The decrypted plaintext.
     */
    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, TRIPLE_DES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION);

            iv = Arrays.copyOfRange(ciphertext, 0, cipher.getBlockSize());
            byte[] actualCiphertext = Arrays.copyOfRange(ciphertext, cipher.getBlockSize(), ciphertext.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            return cipher.doFinal(actualCiphertext);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting with Triple DES", e);
        }
    }


    /**
     * Generates a new Triple DES key.
     * This method uses a KeyGenerator to create a random key suitable for Triple DES encryption.
     *
     * @return The generated Triple DES key.
     */
    @Override
    public byte[] generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(TRIPLE_DES_ALGORITHM);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating Triple DES key", e);
        }
    }


    /**
     * Generates a new Triple DES key based on the provided seed.
     * The seed is used to initialize a SecureRandom instance, ensuring reproducibility of the key.
     *
     * @param seed The seed used for key generation.
     * @return The generated Triple DES key.
     */
    @Override
    public byte[] generateKey(byte[] seed) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(TRIPLE_DES_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            keyGenerator.init(secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating Triple DES key with seed", e);
        }
    }


    /**
     * Returns the encryption algorithm used, which is Triple DES in this implementation.
     *
     * @return The encryption algorithm, Triple DES.
     */
    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return EncryptionAlgorithm.TRIPLE_DES;
    }
}