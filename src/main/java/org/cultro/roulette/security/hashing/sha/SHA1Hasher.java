package org.cultro.roulette.security.hashing.sha;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Hasher implements Hash {

    private final MessageDigest sha1Digest;

    public SHA1Hasher() {
        try {
            this.sha1Digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not available", e);
        }
    }

    @Override
    public byte[] digest(byte[] input) {
        return sha1Digest.digest(input);
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
        return HashAlgorithm.SHA1;
    }

    @Override
    public int getHashLength() {
        return 20;
    }
}