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
 * Implementation of the SymmetricCipher interface for the DES (Data Encryption Standard) algorithm.
 * DES is a symmetric key algorithm used for secure data encryption and decryption.
 * This class utilizes DES in CBC (Cipher Block Chaining) mode with PKCS5 padding.
 */
public class DESCipher implements SymmetricCipher {

    private static final String DES_ALGORITHM = "DES";
    private static final String DES_TRANSFORMATION = "DES/CBC/PKCS5Padding";
    private byte[] iv;


    /**
     * Encrypts the given plaintext using the specified DES key.
     * The method initializes a DES cipher in encrypt mode with a randomly generated IV (Initialization Vector),
     * combines the IV with the encrypted data, and returns the result.
     *
     * @param plaintext The data to be encrypted.
     * @param key       The DES key to use for encryption.
     * @return The encrypted data with the IV prepended.
     */
    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, DES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(DES_TRANSFORMATION);

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
            throw new RuntimeException("Error while encrypting with DES", e);
        }
    }


    /**
     * Decrypts the given ciphertext using the specified DES key.
     * The method extracts the IV from the beginning of the ciphertext, initializes a DES cipher in decrypt mode,
     * and decrypts the remaining data.
     *
     * @param ciphertext The data to be decrypted.
     * @param key        The DES key to use for decryption.
     * @return The decrypted plaintext.
     */
    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, DES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(DES_TRANSFORMATION);

            iv = Arrays.copyOfRange(ciphertext, 0, cipher.getBlockSize());
            byte[] actualCiphertext = Arrays.copyOfRange(ciphertext, cipher.getBlockSize(), ciphertext.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            return cipher.doFinal(actualCiphertext);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting with DES", e);
        }
    }


    /**
     * Generates a new DES key.
     * This method utilizes a KeyGenerator to produce a random DES key.
     *
     * @return The generated DES key.
     */
    @Override
    public byte[] generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(DES_ALGORITHM);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating DES key", e);
        }
    }


    /**
     * Generates a new DES key based on the provided seed.
     * This method utilizes a KeyGenerator with a SecureRandom instance seeded with the provided seed.
     *
     * @param seed The seed to use for key generation.
     * @return The generated DES key.
     */
    @Override
    public byte[] generateKey(byte[] seed) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(DES_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            keyGenerator.init(secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating DES key with seed", e);
        }
    }


    /**
     * Retrieves the symmetric encryption algorithm used by this implementation.
     *
     * @return The encryption algorithm, DES.
     */
    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return EncryptionAlgorithm.DES;
    }
}