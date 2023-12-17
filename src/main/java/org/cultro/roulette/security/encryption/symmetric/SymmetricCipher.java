package org.cultro.roulette.security.encryption.symmetric;

import org.cultro.roulette.security.encryption.EncryptionAlgorithm;

/**
 * Represents a generic symmetric encryption and decryption interface.
 */
public interface SymmetricCipher {

    /**
     * Encrypts the provided plaintext using the specified key.
     *
     * @param plaintext The data to be encrypted.
     * @param key The symmetric key to use for encryption.
     * @return The encrypted data.
     */
    byte[] encrypt(byte[] plaintext, byte[] key);


    /**
     * Decrypts the provided ciphertext using the specified key.
     *
     * @param ciphertext The data to be decrypted.
     * @param key The symmetric key to use for decryption.
     * @return The decrypted data.
     */
    byte[] decrypt(byte[] ciphertext, byte[] key);


    /**
     * Generates a new symmetric key suitable for this cipher.
     *
     * @return The generated symmetric key.
     */
    byte[] generateKey();


    /**
     * Generates a new symmetric key based on the provided seed.
     *
     * @param seed The seed to use for key generation.
     * @return The generated symmetric key.
     */
    byte[] generateKey(byte[] seed);


    /**
     * Retrieves the symmetric encryption algorithm used by the implementation.
     *
     * @return The encryption algorithm.
     */
    EncryptionAlgorithm getEncryptionAlgorithm();
}