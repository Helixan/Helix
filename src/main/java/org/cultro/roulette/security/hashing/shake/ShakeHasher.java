package org.cultro.roulette.security.hashing.shake;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.SHAKEDigest;

public class ShakeHasher implements Hash {

    private final int length;
    private final SHAKEDigest shakeDigest;

    public ShakeHasher(int length) {
        this.length = length;
        if (length <= 16) {
            this.shakeDigest = new SHAKEDigest(128);
        } else {
            this.shakeDigest = new SHAKEDigest(256);
        }
    }

    @Override
    public byte[] digest(byte[] input) {
        shakeDigest.update(input, 0, input.length);
        byte[] out = new byte[length];
        shakeDigest.doFinal(out, 0, length);
        return out;
    }

    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return digest(combinedInput);
    }

    @Override
    public HashAlgorithm getHashAlgorithm() {
        return (shakeDigest.getDigestSize() == 16) ? HashAlgorithm.SHAKE128 : HashAlgorithm.SHAKE256;
    }

    @Override
    public int getHashLength() {
        return length;
    }
}