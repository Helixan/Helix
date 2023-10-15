package org.cultro.roulette.security.hashing.ripemd;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.RIPEMD256Digest;

public class RIPEMD256Hasher implements Hash {

    private final RIPEMD256Digest ripemd256Digest;

    public RIPEMD256Hasher() {
        this.ripemd256Digest = new RIPEMD256Digest();
    }

    @Override
    public byte[] digest(byte[] input) {
        ripemd256Digest.update(input, 0, input.length);
        byte[] out = new byte[ripemd256Digest.getDigestSize()];
        ripemd256Digest.doFinal(out, 0);
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
        return HashAlgorithm.RIPEMD256;
    }

    @Override
    public int getHashLength() {
        return 32;
    }
}