package org.cultro.helix.security.encryption.symmetric.blowfish;

import org.cultro.helix.security.encryption.EncryptionAlgorithm;
import org.cultro.helix.security.encryption.symmetric.SymmetricCipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Implementation of the SymmetricCipher interface using the Blowfish encryption algorithm.
 * Blowfish is a symmetric-key block cipher, known for its speed and effectiveness.
 * This class provides functionality for encrypting and decrypting data using the Blowfish algorithm
 * in CBC mode with PKCS5 padding.
 */
public class BlowfishCipher implements SymmetricCipher {

    private static final String BLOWFISH_ALGORITHM = "Blowfish";
    private static final String BLOWFISH_TRANSFORMATION = "Blowfish/CBC/PKCS5Padding";
    private byte[] iv;


    /**
     * Encrypts the given plaintext using the specified Blowfish key.
     * Encrypts the data using CBC mode with PKCS5 padding.
     * An Initialization Vector (IV) is generated randomly for each encryption operation.
     *
     * @param plaintext The plaintext to encrypt.
     * @param key       The Blowfish key to use for encryption.
     * @return The encrypted data, including the IV prepended to the actual ciphertext.
     */
    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, BLOWFISH_ALGORITHM);
            Cipher cipher = Cipher.getInstance(BLOWFISH_TRANSFORMATION);

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
            throw new RuntimeException("Error while encrypting with Blowfish", e);
        }
    }


    /**
     * Decrypts the given ciphertext using the specified Blowfish key.
     * Decrypts the data using CBC mode with PKCS5 padding.
     * The IV is extracted from the beginning of the ciphertext.
     *
     * @param ciphertext The ciphertext to decrypt.
     * @param key        The Blowfish key to use for decryption.
     * @return The decrypted plaintext.
     */
    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, BLOWFISH_ALGORITHM);
            Cipher cipher = Cipher.getInstance(BLOWFISH_TRANSFORMATION);

            iv = Arrays.copyOfRange(ciphertext, 0, cipher.getBlockSize());
            byte[] actualCiphertext = Arrays.copyOfRange(ciphertext, cipher.getBlockSize(), ciphertext.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            return cipher.doFinal(actualCiphertext);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting with Blowfish", e);
        }
    }


    /**
     * Generates a new Blowfish key.
     * This method uses a KeyGenerator to create a random key suitable for Blowfish encryption.
     *
     * @return The generated Blowfish key.
     */
    @Override
    public byte[] generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(BLOWFISH_ALGORITHM);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating Blowfish key", e);
        }
    }


    /**
     * Generates a new Blowfish key based on the provided seed.
     * The seed is used to initialize a SecureRandom instance, ensuring reproducibility of the key.
     *
     * @param seed The seed used for key generation.
     * @return The generated Blowfish key.
     */
    @Override
    public byte[] generateKey(byte[] seed) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(BLOWFISH_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            keyGenerator.init(secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating Blowfish key with seed", e);
        }
    }


    /**
     * Returns the encryption algorithm used, which is Blowfish in this implementation.
     *
     * @return The encryption algorithm, Blowfish.
     */
    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return EncryptionAlgorithm.BLOWFISH;
    }
}