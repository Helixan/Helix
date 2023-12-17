package org.cultro.roulette.security.hashing.sha;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class implements the Hash interface using the SHA-256 (Secure Hash Algorithm 256-bit) hashing algorithm.
 * It provides methods for computing hash digests using SHA-256.
 */
public class SHA256Hasher implements Hash {

    private final MessageDigest sha256Digest;


    /**
     * Constructs a new SHA256Hasher by initializing the SHA-256 hashing algorithm.
     * Throws a runtime exception if the SHA-256 algorithm is not available.
     */
    public SHA256Hasher() {
        try {
            this.sha256Digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }


    /**
     * Computes the hash digest of the given input using the SHA-256 algorithm.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (32 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        return sha256Digest.digest(input);
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the SHA-256 algorithm.
     * This method combines the input array and salt array before computing the hash.
     *
     * @param input The byte array to be hashed.
     * @param salt  The salt to be appended to the input before hashing.
     * @return The hashed byte array (32 bytes).
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return digest(combinedInput);
    }


    /**
     * Retrieves the specific hashing algorithm used by this SHA256 hasher.
     *
     * @return The hashing algorithm, SHA-256.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.SHA256;
    }


    /**
     * Retrieves the length of the hash produced by this SHA256 hasher.
     *
     * @return The length of the hash in bytes (32 bytes).
     */
    @Override
    public int getHashLength() {
        return 32;
    }
}