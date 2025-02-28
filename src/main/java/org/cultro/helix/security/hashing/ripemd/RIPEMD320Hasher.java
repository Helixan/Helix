package org.cultro.helix.security.hashing.ripemd;

import org.cultro.helix.security.hashing.Hash;
import org.cultro.helix.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.RIPEMD320Digest;

/**
 * This class implements the Hash interface using the RIPEMD-320 (RACE Integrity Primitives Evaluation Message Digest) hashing algorithm.
 * RIPEMD-320 is an extension of the RIPEMD family, providing a 320-bit hash size. It offers methods for computing hash digests using RIPEMD-320.
 */
public class RIPEMD320Hasher implements Hash {

    private final RIPEMD320Digest ripemd320Digest;


    /**
     * Constructs a new RIPEMD320Hasher by initializing the RIPEMD-320 hashing algorithm.
     */
    public RIPEMD320Hasher() {
        this.ripemd320Digest = new RIPEMD320Digest();
    }


    /**
     * Computes the hash digest of the given input using the RIPEMD-320 algorithm.
     * This method updates the digest using the input byte array and then finalizes the hash computation.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (40 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        ripemd320Digest.update(input, 0, input.length);
        byte[] out = new byte[ripemd320Digest.getDigestSize()];
        ripemd320Digest.doFinal(out, 0);
        return out;
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the RIPEMD-320 algorithm.
     * This method first combines the input array and salt array before performing the hash computation.
     *
     * @param input The byte array to be hashed.
     * @param salt  The salt to be appended to the input before hashing.
     * @return The hashed byte array (40 bytes).
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return digest(combinedInput);
    }


    /**
     * Retrieves the specific hashing algorithm used by this RIPEMD320 hasher.
     *
     * @return The hashing algorithm, RIPEMD-320.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.RIPEMD320;
    }


    /**
     * Retrieves the length of the hash produced by this RIPEMD320 hasher.
     * RIPEMD-320 generates a hash of 40 bytes.
     *
     * @return The length of the hash in bytes (40 bytes).
     */
    @Override
    public int getHashLength() {
        return 40;
    }
}