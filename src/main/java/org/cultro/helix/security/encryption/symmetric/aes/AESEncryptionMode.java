package org.cultro.helix.security.encryption.symmetric.aes;

/**
 * Enumeration of AES encryption modes.
 * Each enum constant represents a specific mode of operation for the AES encryption algorithm.
 * This includes different block modes and padding schemes.
 */
public enum AESEncryptionMode {

    /**
     * AES encryption in Electronic Codebook (ECB) mode with PKCS5 padding.
     */
    AES_ECB_PKCS5PADDING("AES/ECB/PKCS5Padding"),

    /**
     * AES encryption in Cipher Block Chaining (CBC) mode with PKCS5 padding.
     */
    AES_CBC_PKCS5PADDING("AES/CBC/PKCS5Padding"),

    /**
     * AES encryption in Cipher Feedback (CFB) mode with no padding.
     */
    AES_CFB_NOPADDING("AES/CFB/NoPadding"),

    /**
     * AES encryption in Cipher Feedback (CFB) mode with PKCS5 padding.
     */
    AES_CFB_PKCS5PADDING("AES/CFB/PKCS5Padding"),

    /**
     * AES encryption in Output Feedback (OFB) mode with no padding.
     */
    AES_OFB_NOPADDING("AES/OFB/NoPadding"),

    /**
     * AES encryption in Output Feedback (OFB) mode with PKCS5 padding.
     */
    AES_OFB_PKCS5PADDING("AES/OFB/PKCS5Padding");

    private final String mode;


    /**
     * Constructor for AESEncryptionMode enum.
     *
     * @param mode The string representation of the AES mode (e.g., "AES/CBC/PKCS5Padding").
     */
    AESEncryptionMode(String mode) {
        this.mode = mode;
    }


    /**
     * Gets the mode string associated with the AES encryption mode.
     *
     * @return The mode string (e.g., "AES/CBC/PKCS5Padding").
     */
    public String getMode() {
        return mode;
    }
}