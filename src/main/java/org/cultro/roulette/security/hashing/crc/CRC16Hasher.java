package org.cultro.roulette.security.hashing.crc;

import org.cultro.roulette.security.hashing.Hash;
import org.cultro.roulette.security.hashing.HashAlgorithm;

/**
 * Implementation of the Hash interface for the CRC16 algorithm.
 * CRC16 (Cyclic Redundancy Check) is a common checksum algorithm used to detect errors in data.
 */
public class CRC16Hasher implements Hash {

    /**
     * Computes the CRC16 hash digest of the given input.
     * This method applies the CRC16 algorithm to calculate the checksum from the input byte array.
     *
     * @param input The byte array to be hashed.
     * @return The hashed byte array, representing the CRC16 checksum.
     */
    @Override
    public byte[] digest(byte[] input) {
        return computeCRC16(input);
    }


    /**
     * Computes the CRC16 hash digest of the given input with optional prefix and suffix salts.
     * The resulting hash is computed over the concatenated value of prefixSalt, input, and suffixSalt.
     * This allows for additional data manipulation before the CRC16 computation.
     *
     * @param input The byte array to be hashed.
     * @param salt  The byte array to be appended to the input before hashing.
     * @return The hashed byte array, representing the CRC16 checksum.
     */
    @Override
    public byte[] digest(byte[] input, byte[] salt) {
        byte[] combinedInput = new byte[input.length + salt.length];
        System.arraycopy(input, 0, combinedInput, 0, input.length);
        System.arraycopy(salt, 0, combinedInput, input.length, salt.length);

        return computeCRC16(combinedInput);
    }


    /**
     * Retrieves the specific hashing algorithm used by the implementation, CRC16.
     *
     * @return The hashing algorithm, CRC16.
     */
    @Override
    public HashAlgorithm getHashAlgorithm() {
        return HashAlgorithm.CRC16;
    }


    /**
     * Retrieves the length of the hash produced by the implementation.
     * The CRC16 algorithm produces a 2-byte checksum.
     *
     * @return The length of the hash in bytes (2 bytes).
     */
    @Override
    public int getHashLength() {
        return 2;
    }


    /**
     * Computes the CRC16 hash of the given byte array.
     * This private method encapsulates the CRC16 algorithm logic.
     *
     * @param data The byte array to be hashed.
     * @return The CRC16 hash as a byte array.
     */
    private byte[] computeCRC16(byte[] data) {
        int crc = 0xFFFF;

        for (byte b : data) {
            crc = ((crc >>> 8) | (crc << 8)) & 0xFFFF;
            crc ^= (b & 0xFF);
            crc ^= ((crc & 0xFF) >> 4);
            crc ^= (crc << 12) & 0xFFFF;
            crc ^= ((crc & 0xFF) << 5) & 0xFFFF;
        }

        byte[] result = new byte[2];
        result[0] = (byte) (crc & 0xFF);
        result[1] = (byte) ((crc >> 8) & 0xFF);
        return result;
    }
}