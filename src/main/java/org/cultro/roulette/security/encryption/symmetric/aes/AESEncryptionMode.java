package org.cultro.roulette.security.encryption.symmetric.aes;

public enum AESEncryptionMode {

    AES_ECB_PKCS5PADDING("AES/ECB/PKCS5Padding"),
    AES_CBC_PKCS5PADDING("AES/CBC/PKCS5Padding"),
    AES_CFB_NOPADDING("AES/CFB/NoPadding"),
    AES_CFB_PKCS5PADDING("AES/CFB/PKCS5Padding"),
    AES_OFB_NOPADDING("AES/OFB/NoPadding"),
    AES_OFB_PKCS5PADDING("AES/OFB/PKCS5Padding");

    private final String mode;

    AESEncryptionMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
