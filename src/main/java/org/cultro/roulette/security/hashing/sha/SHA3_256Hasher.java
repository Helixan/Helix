package org.cultro.roulette.security.hashing.sha;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;
import org.bouncycastle.crypto.digests.SHA3Digest;

public class SHA3_256Hasher implements Hash {

    private final SHA3Digest sha3_256Digest;

    public SHA3_256Hasher() {
        this.sha3_256Digest = new SHA3Digest(256);
    }

    @Override
    public byte[] digest(byte[] input) {
        sha3_256Digest.update(input, 0, input.length);
        byte[] out = new byte[sha3_256Digest.getDigestSize()];
        sha3_256Digest.doFinal(out, 0);
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
        return HashAlgorithm.SHA3_256;
    }

    @Override
    public int getHashLength() {
        return 32;
    }
}