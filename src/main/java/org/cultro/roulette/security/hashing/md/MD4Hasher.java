package org.cultro.roulette.security.hashing.md;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.MD4Digest;

/**
 * This class implements the Hash interface using the MD4 (Message-Digest Algorithm 4) hashing algorithm.
 * MD4 is an early hash function designed by Ronald Rivest and provides a 128-bit hash size. It offers methods for computing hash digests using MD4.
 */
public class MD4Hasher implements Hash {

    private final MD4Digest md4Digest;


    /**
     * Constructs a new MD4Hasher by initializing the MD4 hashing algorithm.
     */
    public MD4Hasher() {
        this.md4Digest = new MD4Digest();
    }


    /**
     * Computes the hash digest of the given input using the MD4 algorithm.
     * This method updates the digest using the input byte array and then finalizes the hash computation.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (16 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        md4Digest.update(input, 0, input.length);
        byte[] out = new byte[md4Digest.getDigestSize()];
        md4Digest.doFinal(out, 0);
        return out;
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the MD4 algorithm.
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
     * Retrieves the specific hashing algorithm used by this MD4 hasher.
     *
     * @return The hashing algorithm, MD4.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.MD4;
    }


    /**
     * Retrieves the length of the hash produced by this MD4 hasher.
     * MD4 generates a hash of 16 bytes.
     *
     * @return The length of the hash in bytes (16 bytes).
     */
    @Override
    public int getHashLength() {
        return 16;
    }
}
