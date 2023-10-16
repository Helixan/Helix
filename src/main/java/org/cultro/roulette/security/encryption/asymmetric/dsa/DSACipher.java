package org.cultro.roulette.security.encryption.asymmetric.dsa;

import org.cultro.roulette.security.encryption.EncryptionAlgorithm;
import org.cultro.roulette.security.encryption.asymmetric.AsymmetricCipher;

import java.security.*;

public class DSACipher implements AsymmetricCipher {

    private static final String DSA_ALGORITHM = "DSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withDSA";

    private final int keySize;

    public DSACipher(int keySize) {
        this.keySize = keySize;
    }

    public DSACipher() {
        this.keySize = 2048;
    }

    @Override
    public byte[] encrypt(byte[] plaintext, PublicKey publicKey) {
        throw new UnsupportedOperationException("DSA does not support encryption. Use sign method instead.");
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, PrivateKey privateKey) {
        throw new UnsupportedOperationException("DSA does not support decryption. Use verify method instead.");
    }

    public byte[] sign(byte[] data, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("Error signing data with DSA", e);
        }
    }

    public boolean verify(byte[] data, byte[] signature, PublicKey publicKey) {
        try {
            Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
            sig.initVerify(publicKey);
            sig.update(data);
            return sig.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying DSA signature", e);
        }
    }

    @Override
    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(DSA_ALGORITHM);
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating DSA key pair", e);
        }
    }

    @Override
    public KeyPair generateKeyPair(byte[] seed) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(DSA_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            keyPairGenerator.initialize(2048, secureRandom);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating DSA key pair with seed", e);
        }
    }

    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return EncryptionAlgorithm.DSA;
    }
}