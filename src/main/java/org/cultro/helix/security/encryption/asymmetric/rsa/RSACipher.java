package org.cultro.helix.security.encryption.asymmetric.rsa;

import org.cultro.helix.lang.Validate;
import org.cultro.helix.security.encryption.EncryptionAlgorithm;
import org.cultro.helix.security.encryption.asymmetric.AsymmetricCipher;

import javax.crypto.Cipher;
import java.security.*;

/**
 * Implements the RSACipher class, providing RSA encryption and decryption capabilities.
 * This class supports operations such as key pair generation, encryption, and decryption using RSA algorithm.
 */
public class RSACipher implements AsymmetricCipher {

    private static final String RSA_ALGORITHM = "RSA";
    private static final String RSA_TRANSFORMATION = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
    private final int keySize;


    /**
     * Constructor for RSACipher with a specified key size.
     *
     * @param keySize The size of the RSA key in bits. Must be greater than or equal to 512.
     */
    public RSACipher(int keySize) {
        Validate.isGreaterThanOrEqualTo(keySize, 512, "Cannot have a RSA key size of less than 512.");
        this.keySize = keySize;
    }


    /**
     * Default constructor for RSACipher, which sets the key size to 2048 bits.
     */
    public RSACipher() {
        this.keySize = 2048;
    }


    /**
     * Encrypts plaintext using the specified public RSA key.
     *
     * @param plaintext The plaintext to be encrypted.
     * @param publicKey The RSA public key.
     * @return The encrypted data.
     */
    @Override
    public byte[] encrypt(byte[] plaintext, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(plaintext);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting with RSA", e);
        }
    }


    /**
     * Decrypts ciphertext using the specified private RSA key.
     *
     * @param ciphertext The ciphertext to be decrypted.
     * @param privateKey The RSA private key.
     * @return The decrypted data.
     */
    @Override
    public byte[] decrypt(byte[] ciphertext, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(ciphertext);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting with RSA", e);
        }
    }


    /**
     * Generates a RSA key pair with the specified key size.
     *
     * @return A KeyPair containing RSA public and private keys.
     */
    @Override
    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            keyPairGenerator.initialize(keySize);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating RSA key pair", e);
        }
    }


    /**
     * Generates a RSA key pair using a specified seed for the random number generator.
     *
     * @param seed The seed to use for key pair generation.
     * @return A KeyPair containing RSA public and private keys.
     */
    @Override
    public KeyPair generateKeyPair(byte[] seed) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            keyPairGenerator.initialize(keySize, secureRandom);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating RSA key pair with seed", e);
        }
    }


    /**
     * Returns the encryption algorithm used, which is RSA in this implementation.
     *
     * @return The encryption algorithm, RSA.
     */
    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return EncryptionAlgorithm.RSA;
    }
}