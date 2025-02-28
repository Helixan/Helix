package org.cultro.helix.security.hashing;

/**
 * Represents a generic hashing interface to provide hashing capabilities.
 * This interface allows for digest generation with optional prefix and suffix salts.
 * Implementations should provide specific hashing algorithms.
 */
public interface Hash {

    /**
     * Computes the hash digest of the given input.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array.
     */
    byte[] digest(byte[] input);


    /**
     * Computes the hash digest of the given input with optional prefix and suffix salts.
     * The resulting hash is computed over the concatenated value of prefixSalt, input, and suffixSalt.
     *
     * @param input       The byte array to be hashed.
     * @param salt        The byte array to be appended to the input before hashing.
     * @return The hashed byte array.
     */
    byte[] digest(byte[] input, byte[] salt);


    /**
     * Retrieves the specific hashing algorithm used by the implementation.
     *
     * @return The hashing algorithm.
     */
    HashAlgorithm getHashAlgorithm();


    /**
     * Retrieves the length of the hash produced by the implementation.
     *
     * @return The length of the hash in bytes.
     */
    int getHashLength();
}