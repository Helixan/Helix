package org.cultro.roulette.security.hashing.keccak;

import org.bouncycastle.crypto.digests.KeccakDigest;
import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

/**
 * This class implements the Hash interface using the Keccak-512 hashing algorithm.
 * It offers methods for computing hash digests with a 512-bit output, providing a high level of security and cryptographic strength.
 */
public class Keccack512Hasher implements Hash {

    private final KeccakDigest keccakDigest;


    /**
     * Constructs a new Keccack512Hasher by initializing the Keccak-512 hashing algorithm.
     */
    public Keccack512Hasher() {
        this.keccakDigest = new KeccakDigest(512);
    }


    /**
     * Computes the hash digest of the given input using the Keccak-512 algorithm.
     * This method updates the digest with the input byte array and then finalizes the hash computation.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (64 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        keccakDigest.update(input, 0, input.length);
        byte[] out = new byte[keccakDigest.getDigestSize()];
        keccakDigest.doFinal(out, 0);
        return out;
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the Keccak-512 algorithm.
     * This method first combines the input array and salt array before performing the hash computation.
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
     * Retrieves the specific hashing algorithm used by this Keccack512 hasher.
     *
     * @return The hashing algorithm, KECCACK_512.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.KECCACK_512;
    }


    /**
     * Retrieves the length of the hash produced by this Keccack512 hasher.
     * KECCACK_512 generates a hash of 64 bytes.
     *
     * @return The length of the hash in bytes (64 bytes).
     */
    @Override
    public int getHashLength() {
        return 64;
    }
}