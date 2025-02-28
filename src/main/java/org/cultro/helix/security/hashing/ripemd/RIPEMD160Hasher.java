package org.cultro.helix.security.hashing.ripemd;

import org.cultro.helix.security.hashing.Hash;
import org.cultro.helix.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;

/**
 * This class implements the Hash interface using the RIPEMD-160 (RACE Integrity Primitives Evaluation Message Digest) hashing algorithm.
 * It provides methods for computing hash digests using RIPEMD-160.
 */
public class RIPEMD160Hasher implements Hash {

    private final RIPEMD160Digest ripemd160Digest;


    /**
     * Constructs a new RIPEMD160Hasher by initializing the RIPEMD-160 hashing algorithm.
     */
    public RIPEMD160Hasher() {
        this.ripemd160Digest = new RIPEMD160Digest();
    }


    /**
     * Computes the hash digest of the given input using the RIPEMD-160 algorithm.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (20 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        ripemd160Digest.update(input, 0, input.length);
        byte[] out = new byte[ripemd160Digest.getDigestSize()];
        ripemd160Digest.doFinal(out, 0);
        return out;
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the RIPEMD-160 algorithm.
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
     * Retrieves the specific hashing algorithm used by this RIPEMD160 hasher.
     *
     * @return The hashing algorithm, RIPEMD-160.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.RIPEMD160;
    }


    /**
     * Retrieves the length of the hash produced by this RIPEMD160 hasher.
     *
     * @return The length of the hash in bytes (20 bytes).
     */
    @Override
    public int getHashLength() {
        return 20;
    }
}