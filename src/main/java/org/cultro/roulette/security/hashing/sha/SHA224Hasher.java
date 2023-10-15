package org.cultro.roulette.security.hashing.sha;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.SHA224Digest;

public class SHA224Hasher implements Hash {

    private final SHA224Digest sha224Digest;

    public SHA224Hasher() {
        this.sha224Digest = new SHA224Digest();
    }

    @Override
    public byte[] digest(byte[] input) {
        sha224Digest.update(input, 0, input.length);
        byte[] out = new byte[sha224Digest.getDigestSize()];
        sha224Digest.doFinal(out, 0);
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
        return HashAlgorithm.SHA224;
    }

    @Override
    public int getHashLength() {
        return 28;
    }
}





