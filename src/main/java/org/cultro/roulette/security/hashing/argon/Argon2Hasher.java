package org.cultro.roulette.security.hashing.argon;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.cultro.roulette.lang.Validate;
import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

/**
 * Implementation of the Hash interface for the Argon2 algorithm.
 */
public class Argon2Hasher implements Hash {

    private final int memoryCost;
    private final int parallelism;
    private final int iterations;
    private final int parameters;

    private final int length;

    public Argon2Hasher(int memoryCost, int parallelism, int iterations, int length, Argon2Type type) {
        Validate.notNull(type, "Cannot compute the Argon2 hashing algorithm for a null type.");
        this.memoryCost = memoryCost;
        this.parallelism = parallelism;
        this.iterations = iterations;
        this.length = length;
        this.parameters = type.getType();
    }

    public Argon2Hasher(int memoryCost, int parallelism, int iterations) {
        this(memoryCost, parallelism, iterations, 32, Argon2Type.ARGON2_di);
    }

    public Argon2Hasher(int memoryCost, int parallelism, int iterations, int length) {
        this(memoryCost, parallelism, iterations, length, Argon2Type.ARGON2_di);
    }

    public Argon2Hasher(int memoryCost, int parallelism, int iterations, Argon2Type type) {
        this(memoryCost, parallelism, iterations, 32, type);
    }

    @Override
    public byte[] digest(byte[] input) {
        return computeArgon2(input, null);
    }

    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        return computeArgon2(input, salt);
    }

    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.ARGON2;
    }

    @Override
    public int getHashLength() {
        return length;
    }

    private byte[] computeArgon2(byte[] password, byte[] salt) {
        Argon2Parameters params = new Argon2Parameters.Builder(parameters)
                .withSalt(salt)
                .withParallelism(parallelism)
                .withMemoryAsKB(memoryCost)
                .withIterations(iterations)
                .build();

        Argon2BytesGenerator gen = new Argon2BytesGenerator();
        gen.init(params);

        byte[] result = new byte[getHashLength()];
        gen.generateBytes(password, result, 0, result.length);

        return result;
    }
}
