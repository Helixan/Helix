package org.cultro.roulette.security.hashing.keccak;

import org.bouncycastle.crypto.digests.KeccakDigest;
import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

public class Keccack224Hasher implements Hash {

    private final KeccakDigest keccakDigest;

    public Keccack224Hasher() {
        this.keccakDigest = new KeccakDigest(224);
    }

    @Override
    public byte[] digest(byte[] input) {
        keccakDigest.update(input, 0, input.length);
        byte[] out = new byte[keccakDigest.getDigestSize()];
        keccakDigest.doFinal(out, 0);
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
        return HashAlgorithm.KECCACK_224;
    }

    @Override
    public int getHashLength() {
        return 28;
    }
}
