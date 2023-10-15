package org.cultro.roulette.security.hashing.md;

import org.bouncycastle.crypto.digests.MD2Digest;
import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

public class MD2Hasher implements Hash {

    private final MD2Digest md2Digest;

    public MD2Hasher() {
        this.md2Digest = new MD2Digest();
    }

    @Override
    public byte[] digest(byte[] input) {
        md2Digest.update(input, 0, input.length);
        byte[] out = new byte[md2Digest.getDigestSize()];
        md2Digest.doFinal(out, 0);
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
        return HashAlgorithm.MD2;
    }

    @Override
    public int getHashLength() {
        return 16;
    }
}