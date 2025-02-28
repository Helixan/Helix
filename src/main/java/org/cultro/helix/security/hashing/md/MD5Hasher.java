package org.cultro.helix.security.hashing.md;

import org.cultro.helix.security.hashing.Hash;
import org.cultro.helix.security.hashing.HashAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class implements the Hash interface using the MD5 (Message-Digest Algorithm 5) hashing algorithm.
 * MD5 is a widely used cryptographic hash function that produces a 128-bit (16-byte) hash value. It is commonly used to verify data integrity.
 */
public class MD5Hasher implements Hash {

    private final MessageDigest md5Digest;


    /**
     * Constructs a new MD5Hasher by initializing the MD5 hashing algorithm.
     * Throws a runtime exception if the MD5 algorithm is not available.
     */
    public MD5Hasher() {
        try {
            this.md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }


    /**
     * Computes the hash digest of the given input using the MD5 algorithm.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (16 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        return md5Digest.digest(input);
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the MD5 algorithm.
     * This method combines the input array and salt array before computing the hash.
     *
     * @param input The byte array to be hashed.
     * @param salt  The salt to be appended to the input before hashing.
     * @return The hashed byte array (16 bytes).
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return digest(combinedInput);
    }


    /**
     * Retrieves the specific hashing algorithm used by this MD5 hasher.
     *
     * @return The hashing algorithm, MD5.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.MD5;
    }


    /**
     * Retrieves the length of the hash produced by this MD5 hasher.
     * MD5 generates a hash of 16 bytes.
     *
     * @return The length of the hash in bytes (16 bytes).
     */
    @Override
    public int getHashLength() {
        return 16;
    }
}
