package org.cultro.helix.security.hashing.md;

import org.bouncycastle.crypto.digests.MD2Digest;
import org.cultro.helix.security.hashing.Hash;
import org.cultro.helix.security.hashing.HashAlgorithm;

/**
 * This class implements the Hash interface using the MD2 (Message-Digest Algorithm 2) hashing algorithm.
 * MD2 is one of the earliest hash functions and provides a 128-bit hash size. It offers methods for computing hash digests using MD2.
 */
public class MD2Hasher implements Hash {

    private final MD2Digest md2Digest;


    /**
     * Constructs a new MD2Hasher by initializing the MD2 hashing algorithm.
     */
    public MD2Hasher() {
        this.md2Digest = new MD2Digest();
    }


    /**
     * Computes the hash digest of the given input using the MD2 algorithm.
     * This method updates the digest using the input byte array and then finalizes the hash computation.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (16 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        md2Digest.update(input, 0, input.length);
        byte[] out = new byte[md2Digest.getDigestSize()];
        md2Digest.doFinal(out, 0);
        return out;
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the MD2 algorithm.
     * This method first combines the input array and salt array before performing the hash computation.
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
     * Retrieves the specific hashing algorithm used by this MD2 hasher.
     *
     * @return The hashing algorithm, MD2.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.MD2;
    }


    /**
     * Retrieves the length of the hash produced by this MD2 hasher.
     * MD2 generates a hash of 16 bytes.
     *
     * @return The length of the hash in bytes (16 bytes).
     */
    @Override
    public int getHashLength() {
        return 16;
    }
}