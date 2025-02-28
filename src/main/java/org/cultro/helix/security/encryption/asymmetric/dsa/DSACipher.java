package org.cultro.helix.security.encryption.asymmetric.dsa;

import org.cultro.helix.security.encryption.EncryptionAlgorithm;
import org.cultro.helix.security.encryption.asymmetric.AsymmetricCipher;

import java.security.*;

/**
 * Implements the DSACipher class, providing digital signature capabilities using the DSA algorithm.
 * This class supports operations such as key pair generation, signing, and signature verification.
 */
public class DSACipher implements AsymmetricCipher {

    private static final String DSA_ALGORITHM = "DSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withDSA";
    private final int keySize;


    /**
     * Constructor for DSACipher with a specified key size.
     *
     * @param keySize The size of the DSA key in bits.
     */
    public DSACipher(int keySize) {
        this.keySize = keySize;
    }


    /**
     * Default constructor for DSACipher, which sets the key size to 2048 bits.
     */
    public DSACipher() {
        this.keySize = 2048;
    }


    /**
     * This method is not supported in DSA as it is not an encryption algorithm.
     *
     * @param plaintext The data to be encrypted.
     * @param publicKey The public key used for encryption.
     * @return Nothing as DSA does not support encryption.
     * @throws UnsupportedOperationException Always thrown as DSA does not support encryption.
     */
    @Override
    public byte[] encrypt(byte[] plaintext, PublicKey publicKey) {
        throw new UnsupportedOperationException("DSA does not support encryption. Use sign method instead.");
    }


    /**
     * This method is not supported in DSA as it is not a decryption algorithm.
     *
     * @param ciphertext The data to be decrypted.
     * @param privateKey The private key used for decryption.
     * @return Nothing as DSA does not support decryption.
     * @throws UnsupportedOperationException Always thrown as DSA does not support decryption.
     */
    @Override
    public byte[] decrypt(byte[] ciphertext, PrivateKey privateKey) {
        throw new UnsupportedOperationException("DSA does not support decryption. Use verify method instead.");
    }


    /**
     * Signs the given data using the provided private DSA key.
     *
     * @param data The data to be signed.
     * @param privateKey The DSA private key used for signing.
     * @return The digital signature.
     */
    public byte[] sign(byte[] data, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("Error signing data with DSA", e);
        }
    }


    /**
     * Verifies a DSA signature using the specified public key.
     *
     * @param data The data that was signed.
     * @param signature The digital signature.
     * @param publicKey The DSA public key used for verification.
     * @return True if the signature is valid, false otherwise.
     */
    public boolean verify(byte[] data, byte[] signature, PublicKey publicKey) {
        try {
            Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
            sig.initVerify(publicKey);
            sig.update(data);
            return sig.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying DSA signature", e);
        }
    }


    /**
     * Generates a DSA key pair with the default key size.
     *
     * @return A KeyPair containing DSA public and private keys.
     */
    @Override
    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(DSA_ALGORITHM);
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating DSA key pair", e);
        }
    }


    /**
     * Generates a DSA key pair using a specified seed for the random number generator.
     *
     * @param seed The seed to use for key pair generation.
     * @return A KeyPair containing DSA public and private keys.
     */
    @Override
    public KeyPair generateKeyPair(byte[] seed) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(DSA_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            keyPairGenerator.initialize(2048, secureRandom);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating DSA key pair with seed", e);
        }
    }


    /**
     * Returns the encryption algorithm used, which is DSA in this implementation.
     *
     * @return The encryption algorithm, DSA.
     */
    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return EncryptionAlgorithm.DSA;
    }
}