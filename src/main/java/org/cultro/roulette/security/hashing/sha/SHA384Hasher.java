package org.cultro.roulette.security.hashing.sha;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class implements the Hash interface using the SHA-384 (Secure Hash Algorithm 384-bit) hashing algorithm.
 * It provides methods for computing hash digests using SHA-384.
 */
public class SHA384Hasher implements Hash {

    private final MessageDigest sha384Digest;


    /**
     * Constructs a new SHA384Hasher by initializing the SHA-384 hashing algorithm.
     * Throws a runtime exception if the SHA-384 algorithm is not available.
     */
    public SHA384Hasher() {
        try {
            this.sha384Digest = MessageDigest.getInstance("SHA-384");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-384 algorithm not available", e);
        }
    }


    /**
     * Computes the hash digest of the given input using the SHA-384 algorithm.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (48 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        return sha384Digest.digest(input);
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the SHA-384 algorithm.
     * This method combines the input array and salt array before computing the hash.
     *
     * @param input The byte array to be hashed.
     * @param salt  The salt to be appended to the input before hashing.
     * @return The hashed byte array (48 bytes).
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return digest(combinedInput);
    }


    /**
     * Retrieves the specific hashing algorithm used by this SHA384 hasher.
     *
     * @return The hashing algorithm, SHA-384.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.SHA384;
    }


    /**
     * Retrieves the length of the hash produced by this SHA384 hasher.
     *
     * @return The length of the hash in bytes (48 bytes).
     */
    @Override
    public int getHashLength() {
        return 48;
    }
}