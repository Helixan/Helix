package org.cultro.helix.security.hashing.sha;

import org.cultro.helix.security.hashing.Hash;
import org.cultro.helix.security.hashing.HashAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class implements the Hash interface using the SHA-512 (Secure Hash Algorithm 512-bit) hashing algorithm.
 * It provides methods for computing hash digests using SHA-512.
 */
public class SHA512Hasher implements Hash {

    private final MessageDigest sha512Digest;


    /**
     * Constructs a new SHA512Hasher by initializing the SHA-512 hashing algorithm.
     * Throws a runtime exception if the SHA-512 algorithm is not available.
     */
    public SHA512Hasher() {
        try {
            this.sha512Digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not available", e);
        }
    }


    /**
     * Computes the hash digest of the given input using the SHA-512 algorithm.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (64 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        return sha512Digest.digest(input);
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the SHA-512 algorithm.
     * This method combines the input array and salt array before computing the hash.
     *
     * @param input The byte array to be hashed.
     * @param salt  The salt to be appended to the input before hashing.
     * @return The hashed byte array (64 bytes).
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return digest(combinedInput);
    }


    /**
     * Retrieves the specific hashing algorithm used by this SHA512 hasher.
     *
     * @return The hashing algorithm, SHA-512.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.SHA512;
    }


    /**
     * Retrieves the length of the hash produced by this SHA512 hasher.
     *
     * @return The length of the hash in bytes (64 bytes).
     */
    @Override
    public int getHashLength() {
        return 64;
    }
}