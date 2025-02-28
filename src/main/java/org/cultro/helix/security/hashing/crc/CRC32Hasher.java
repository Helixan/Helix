package org.cultro.helix.security.hashing.crc;

import org.cultro.helix.security.hashing.Hash;
import org.cultro.helix.security.hashing.HashAlgorithm;
import java.util.zip.CRC32;

/**
 * Implementation of the Hash interface for the CRC32 algorithm.
 * CRC32 (Cyclic Redundancy Check) is a commonly used algorithm for generating a checksum to detect errors in data.
 * It is widely used due to its simplicity and effectiveness in detecting common types of data corruption.
 */
public class CRC32Hasher implements Hash {

    /**
     * Computes the CRC32 hash digest of the given input.
     * This method applies the CRC32 algorithm to calculate the checksum from the input byte array.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array, representing the CRC32 checksum.
     */
    @Override
    public byte[] digest(byte[] input) {
        return computeCRC32(input);
    }


    /**
     * Computes the CRC32 hash digest of the given input with optional prefix and suffix salts.
     * The resulting hash is computed over the concatenated value of prefixSalt, input, and suffixSalt.
     * This allows for additional data manipulation before the CRC32 computation.
     *
     * @param input The byte array to be hashed.
     * @param salt  The byte array to be appended to the input before hashing.
     * @return The hashed byte array, representing the CRC32 checksum.
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return computeCRC32(combinedInput);
    }


    /**
     * Retrieves the specific hashing algorithm used by the implementation, CRC32.
     *
     * @return The hashing algorithm, CRC32.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.CRC32;
    }


    /**
     * Retrieves the length of the hash produced by the implementation.
     * The CRC32 algorithm produces a 4-byte checksum.
     *
     * @return The length of the hash in bytes (4 bytes).
     */
    @Override
    public int getHashLength() {
        return 4;
    }


    /**
     * Computes the CRC32 hash of the given byte array.
     * This private method encapsulates the CRC32 algorithm logic using the java.util.zip.CRC32 class.
     *
     * @param data The byte array to be hashed.
     * @return The CRC32 hash as a byte array.
     */
    private byte[] computeCRC32(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        long value = crc32.getValue();

        return new byte[] {
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value
        };
    }
}