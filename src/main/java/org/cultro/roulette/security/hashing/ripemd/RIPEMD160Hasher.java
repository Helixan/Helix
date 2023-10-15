package org.cultro.roulette.security.hashing.ripemd;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;

public class RIPEMD160Hasher implements Hash {

    private final RIPEMD160Digest ripemd160Digest;

    public RIPEMD160Hasher() {
        this.ripemd160Digest = new RIPEMD160Digest();
    }

    @Override
    public byte[] digest(byte[] input) {
        ripemd160Digest.update(input, 0, input.length);
        byte[] out = new byte[ripemd160Digest.getDigestSize()];
        ripemd160Digest.doFinal(out, 0);
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
        return HashAlgorithm.RIPEMD160;
    }

    @Override
    public int getHashLength() {
        return 20;
    }
}