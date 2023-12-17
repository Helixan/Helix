package org.cultro.roulette.security.hashing.ripemd;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.RIPEMD256Digest;

/**
 * This class implements the Hash interface using the RIPEMD-256 (RACE Integrity Primitives Evaluation Message Digest) hashing algorithm.
 * RIPEMD-256 is an extended version of the RIPEMD algorithm, offering a 256-bit hash size. It provides methods for computing hash digests using RIPEMD-256.
 */
public class RIPEMD256Hasher implements Hash {

    private final RIPEMD256Digest ripemd256Digest;


    /**
     * Constructs a new RIPEMD256Hasher by initializing the RIPEMD-256 hashing algorithm.
     */
    public RIPEMD256Hasher() {
        this.ripemd256Digest = new RIPEMD256Digest();
    }


    /**
     * Computes the hash digest of the given input using the RIPEMD-256 algorithm.
     * This method updates the digest using the input byte array and then finalizes the hash computation.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (32 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        ripemd256Digest.update(input, 0, input.length);
        byte[] out = new byte[ripemd256Digest.getDigestSize()];
        ripemd256Digest.doFinal(out, 0);
        return out;
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the RIPEMD-256 algorithm.
     * This method first combines the input array and salt array before performing the hash computation.
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
     * Retrieves the specific hashing algorithm used by this RIPEMD256 hasher.
     *
     * @return The hashing algorithm, RIPEMD-256.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.RIPEMD256;
    }


    /**
     * Retrieves the length of the hash produced by this RIPEMD256 hasher.
     * RIPEMD-256 generates a hash of 32 bytes.
     *
     * @return The length of the hash in bytes (32 bytes).
     */
    @Override
    public int getHashLength() {
        return 32;
    }
}