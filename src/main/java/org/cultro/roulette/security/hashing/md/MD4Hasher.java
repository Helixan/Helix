package org.cultro.roulette.security.hashing.md;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.MD4Digest;

public class MD4Hasher implements Hash {

    private final MD4Digest md4Digest;

    public MD4Hasher() {
        this.md4Digest = new MD4Digest();
    }

    @Override
    public byte[] digest(byte[] input) {
        md4Digest.update(input, 0, input.length);
        byte[] out = new byte[md4Digest.getDigestSize()];
        md4Digest.doFinal(out, 0);
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
        return HashAlgorithm.MD4;
    }

    @Override
    public int getHashLength() {
        return 16;
    }
}