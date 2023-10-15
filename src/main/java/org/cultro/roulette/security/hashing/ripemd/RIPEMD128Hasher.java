package org.cultro.roulette.security.hashing.ripemd;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;

public class RIPEMD128Hasher implements Hash {

    private final RIPEMD128Digest ripemd128Digest;

    public RIPEMD128Hasher() {
        this.ripemd128Digest = new RIPEMD128Digest();
    }

    @Override
    public byte[] digest(byte[] input) {
        ripemd128Digest.update(input, 0, input.length);
        byte[] out = new byte[ripemd128Digest.getDigestSize()];
        ripemd128Digest.doFinal(out, 0);
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
        return HashAlgorithm.RIPEMD128;
    }

    @Override
    public int getHashLength() {
        return 16;
    }
}
