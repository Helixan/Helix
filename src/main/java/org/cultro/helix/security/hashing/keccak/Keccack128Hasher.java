package org.cultro.helix.security.hashing.keccak;

import org.bouncycastle.crypto.digests.KeccakDigest;
import org.cultro.helix.security.hashing.Hash;
import org.cultro.helix.security.hashing.HashAlgorithm;

/**
 * This class implements the Hash interface using the Keccak-128 hashing algorithm.
 * It provides methods for computing hash digests using Keccak with a 128-bit output.
 */
public class Keccack128Hasher implements Hash {

    private final KeccakDigest keccakDigest;


    /**
     * Constructs a new Keccack128Hasher by initializing the Keccak-128 hashing algorithm.
     */
    public Keccack128Hasher() {
        this.keccakDigest = new KeccakDigest(128);
    }


    /**
     * Computes the hash digest of the given input using the Keccak-128 algorithm.
     * This method updates the digest with the input byte array and then finalizes the hash computation.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (16 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        keccakDigest.update(input, 0, input.length);
        byte[] out = new byte[keccakDigest.getDigestSize()];
        keccakDigest.doFinal(out, 0);
        return out;
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the Keccak-128 algorithm.
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
     * Retrieves the specific hashing algorithm used by this Keccack128 hasher.
     *
     * @return The hashing algorithm, KECCACK_128.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.KECCACK_128;
    }


    /**
     * Retrieves the length of the hash produced by this Keccack128 hasher.
     * KECCACK_128 generates a hash of 16 bytes.
     *
     * @return The length of the hash in bytes (16 bytes).
     */
    @Override
    public int getHashLength() {
        return 16;
    }
}
