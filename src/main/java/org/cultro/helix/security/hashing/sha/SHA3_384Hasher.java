package org.cultro.helix.security.hashing.sha;

import org.cultro.helix.security.hashing.Hash;
import org.cultro.helix.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.SHA3Digest;

/**
 * This class implements the Hash interface using the SHA3-384 (Secure Hash Algorithm 3 with a 384-bit digest) hashing algorithm.
 * It provides methods for computing hash digests using SHA3-384.
 */
public class SHA3_384Hasher implements Hash {

    private final SHA3Digest sha3_384Digest;


    /**
     * Constructs a new SHA3_384Hasher by initializing the SHA3-384 hashing algorithm.
     */
    public SHA3_384Hasher() {
        this.sha3_384Digest = new SHA3Digest(384);
    }


    /**
     * Computes the hash digest of the given input using the SHA3-384 algorithm.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array (48 bytes).
     */
    @Override
    public byte[] digest(byte[] input) {
        sha3_384Digest.update(input, 0, input.length);
        byte[] out = new byte[sha3_384Digest.getDigestSize()];
        sha3_384Digest.doFinal(out, 0);
        return out;
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the SHA3-384 algorithm.
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
     * Retrieves the specific hashing algorithm used by this SHA3_384 hasher.
     *
     * @return The hashing algorithm, SHA3-384.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.SHA3_384;
    }


    /**
     * Retrieves the length of the hash produced by this SHA3_384 hasher.
     *
     * @return The length of the hash in bytes (48 bytes).
     */
    @Override
    public int getHashLength() {
        return 48;
    }
}