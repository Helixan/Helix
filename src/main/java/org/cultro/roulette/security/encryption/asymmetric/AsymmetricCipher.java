package org.cultro.roulette.security.encryption.asymmetric;

import org.cultro.roulette.security.encryption.EncryptionAlgorithm;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface AsymmetricCipher {


    /**
     * Encrypts the given plaintext using the provided public key.
     *
     * @param plaintext The data to be encrypted.
     * @param publicKey The public key used for encryption.
     * @return The encrypted data.
     */
    byte[] encrypt(byte[] plaintext, PublicKey publicKey);


    /**
     * Decrypts the given ciphertext using the provided private key.
     *
     * @param ciphertext The data to be decrypted.
     * @param privateKey The private key used for decryption.
     * @return The decrypted data.
     */
    byte[] decrypt(byte[] ciphertext, PrivateKey privateKey);


    /**
     * Generates a pair of public and private keys.
     *
     * @return A KeyPair containing the generated public and private keys.
     */
    KeyPair generateKeyPair();


    /**
     * Generates a pair of public and private keys based on a given seed.
     *
     * @param seed The seed used to generate the key pair deterministically.
     * @return A KeyPair containing the generated public and private keys.
     */
    KeyPair generateKeyPair(byte[] seed);


    /**
     * Retrieves the specific asymmetric encryption algorithm used by the implementation.
     *
     * @return The encryption algorithm.
     */
    EncryptionAlgorithm getEncryptionAlgorithm();
}