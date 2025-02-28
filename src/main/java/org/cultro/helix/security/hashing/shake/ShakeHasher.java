package org.cultro.helix.security.hashing.shake;

import org.cultro.helix.security.hashing.Hash;
import org.cultro.helix.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.SHAKEDigest;

/**
 * This class implements the Hash interface using the SHAKE (Secure Hash Algorithm KECCAK)
 * cryptographic hash functions, SHAKE128 and SHAKE256.
 * It provides the functionality to compute hash digests with variable output lengths.
 */
public class ShakeHasher implements Hash {

    private final int length;
    private final SHAKEDigest shakeDigest;


    /**
     * Constructs a new ShakeHasher with the specified output hash length.
     * Initializes the underlying SHAKE digest algorithm based on the length.
     *
     * @param length The desired length of the hash output in bytes.
     */
    public ShakeHasher(int length) {
        this.length = length;
        if (length <= 16) {
            this.shakeDigest = new SHAKEDigest(128);
        } else {
            this.shakeDigest = new SHAKEDigest(256);
        }
    }


    /**
     * Computes the hash digest of the given input using the SHAKE algorithm.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array with the specified output length.
     */
    @Override
    public byte[] digest(byte[] input) {
        shakeDigest.update(input, 0, input.length);
        byte[] out = new byte[length];
        shakeDigest.doFinal(out, 0, length);
        return out;
    }


    /**
     * Computes the hash digest of the given input, concatenating with the provided salt, using the SHAKE algorithm.
     * This method combines the input array and salt array before computing the hash.
     *
     * @param input The byte array to be hashed.
     * @param salt  The salt to be appended to the input before hashing.
     * @return The hashed byte array with the specified output length.
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return digest(combinedInput);
    }


    /**
     * Retrieves the specific hashing algorithm used by this SHAKE hasher.
     *
     * @return The hashing algorithm, either SHAKE128 or SHAKE256.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return (shakeDigest.getDigestSize() == 16) ? HashAlgorithm.SHAKE128 : HashAlgorithm.SHAKE256;
    }


    /**
     *
     * Retrieves the length of the hash produced by this SHAKE hasher.
     *
     * @return The length of the hash in bytes.
     */
    @Override
    public int getHashLength() {
        return length;
    }
}