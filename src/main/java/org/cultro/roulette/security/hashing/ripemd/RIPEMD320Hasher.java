package org.cultro.roulette.security.hashing.ripemd;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.RIPEMD320Digest;

public class RIPEMD320Hasher implements Hash {

    private final RIPEMD320Digest ripemd320Digest;

    public RIPEMD320Hasher() {
        this.ripemd320Digest = new RIPEMD320Digest();
    }

    @Override
    public byte[] digest(byte[] input) {
        ripemd320Digest.update(input, 0, input.length);
        byte[] out = new byte[ripemd320Digest.getDigestSize()];
        ripemd320Digest.doFinal(out, 0);
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
        return HashAlgorithm.RIPEMD320;
    }

    @Override
    public int getHashLength() {
        return 40;
    }
}