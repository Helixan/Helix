package org.cultro.roulette.security.hashing.argon;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.cultro.roulette.lang.Validate;
import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

/**
 * Implementation of the Hash interface for the Argon2 algorithm.
 * Argon2 is an advanced, memory-hard hashing algorithm designed for password hashing and other security applications.
 * This class provides configurable parameters to tailor the hashing process to specific requirements.
 */
public class Argon2Hasher implements Hash {

    private final int memoryCost;
    private final int parallelism;
    private final int iterations;
    private final int length;
    private final int parameters;


    /**
     * Constructs an Argon2Hasher with specified memory cost, parallelism, iterations, length, and Argon2 type.
     * Validates the provided Argon2 type and initializes the hasher with the given parameters.
     *
     * @param memoryCost  The memory cost (in kilobytes).
     * @param parallelism The level of parallelism.
     * @param iterations  The number of iterations.
     * @param length      The length of the generated hash.
     * @param type        The Argon2 type (e.g., ARGON2_di).
     */
    public Argon2Hasher(int memoryCost, int parallelism, int iterations, int length, Argon2Type type) {
        Validate.notNull(type, "Cannot compute the Argon2 hashing algorithm for a null type.");
        this.memoryCost = memoryCost;
        this.parallelism = parallelism;
        this.iterations = iterations;
        this.length = length;
        this.parameters = type.getType();
    }


    /**
     * Constructs an Argon2Hasher with specified memory cost, parallelism, and iterations.
     * Uses a default hash length of 32 bytes and the ARGON2_di variant of Argon2.
     *
     * @param memoryCost  The memory cost (in kilobytes).
     * @param parallelism The level of parallelism.
     * @param iterations  The number of iterations.
     */
    public Argon2Hasher(int memoryCost, int parallelism, int iterations) {
        this(memoryCost, parallelism, iterations, 32, Argon2Type.ARGON2_di);
    }


    /**
     * Constructs an Argon2Hasher with specified memory cost, parallelism, iterations, and length.
     * Uses the ARGON2_di variant of Argon2.
     *
     * @param memoryCost  The memory cost (in kilobytes).
     * @param parallelism The level of parallelism.
     * @param iterations  The number of iterations.
     * @param length      The length of the generated hash.
     */
    public Argon2Hasher(int memoryCost, int parallelism, int iterations, int length) {
        this(memoryCost, parallelism, iterations, length, Argon2Type.ARGON2_di);
    }


    /**
     * Constructs an Argon2Hasher with specified memory cost, parallelism, iterations, and Argon2 type.
     * Uses a default hash length of 32 bytes.
     *
     * @param memoryCost  The memory cost (in kilobytes).
     * @param parallelism The level of parallelism.
     * @param iterations  The number of iterations.
     * @param type        The Argon2 type (e.g., ARGON2_di).
     */
    public Argon2Hasher(int memoryCost, int parallelism, int iterations, Argon2Type type) {
        this(memoryCost, parallelism, iterations, 32, type);
    }


    /**
     * Computes the Argon2 hash of the given input.
     *
     * @param input The byte array to be hashed.
     * @return The Argon2 hash as a byte array.
     */
    @Override
    public byte[] digest(byte[] input) {
        return computeArgon2(input, null);
    }


    /**
     * Computes the Argon2 hash of the given input with an optional salt.
     *
     * @param input The byte array to be hashed.
     * @param salt  The salt to be appended to the input before hashing.
     * @return The Argon2 hash as a byte array.
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        return computeArgon2(input, salt);
    }


    /**
     * Retrieves the specific hashing algorithm used by the implementation, Argon2.
     *
     * @return The hashing algorithm, Argon2.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.ARGON2;
    }


    /**
     * Retrieves the length of the hash produced by the implementation.
     *
     * @return The length of the hash in bytes.
     */
    @Override
    public int getHashLength() {
        return length;
    }


    /**
     * Computes the Argon2 hash of the given password and salt.
     * This private method encapsulates the Argon2 algorithm logic.
     *
     * @param password The byte array to be hashed.
     * @param salt     The salt to be used in the hashing process.
     * @return The Argon2 hash as a byte array.
     */
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