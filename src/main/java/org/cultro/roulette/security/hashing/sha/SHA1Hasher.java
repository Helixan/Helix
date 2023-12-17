package org.cultro.roulette.security.hashing.sha;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class implements the Hash interface using the SHA-1 (Secure Hash Algorithm 1) hashing algorithm.
 * It provides methods for computing hash digests using SHA-1.
 */
public class SHA1Hasher implements Hash {

    private final MessageDigest sha1Digest;


    /**
     * Constructs a new SHA1Hasher by initializing the SHA-1 hashing algorithm.
     * Throws a runtime exception if the SHA-1 algorithm is not available.
     */
    public SHA1Hasher() {
        try {
            this.sha1Digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not available", e);
        }
    }


    /**
     * Computes the hash digest of the given input using the SHA-1 algorithm.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (20 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        return sha1Digest.digest(input);
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the SHA-1 algorithm.
     * This method combines the input array and salt array before computing the hash.
     *
     * @param input The byte array to be hashed.
     * @param salt  The salt to be appended to the input before hashing.
     * @return The hashed byte array (20 bytes).
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return digest(combinedInput);
    }


    /**
     * Retrieves the specific hashing algorithm used by this SHA1 hasher.
     *
     * @return The hashing algorithm, SHA-1.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.SHA1;
    }


    /**
     * Retrieves the length of the hash produced by this SHA1 hasher.
     *
     * @return The length of the hash in bytes (20 bytes).
     */
    @Override
    public int getHashLength() {
        return 20;
    }
}