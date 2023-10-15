package org.cultro.roulette.security.encryption.symmetric.des;

import org.cultro.roulette.security.encryption.EncryptionAlgorithm;
import org.cultro.roulette.security.encryption.symmetric.SymmetricCipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class DESCipher implements SymmetricCipher {

    private static final String DES_ALGORITHM = "DES";
    private static final String DES_TRANSFORMATION = "DES/CBC/PKCS5Padding";
    private byte[] iv;

    @Override
    public byte[] encrypt(byte[] plaintext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, DES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(DES_TRANSFORMATION);

            iv = new byte[cipher.getBlockSize()];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(plaintext);
            byte[] combined = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
            return combined;
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting with DES", e);
        }
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, DES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(DES_TRANSFORMATION);

            iv = Arrays.copyOfRange(ciphertext, 0, cipher.getBlockSize());
            byte[] actualCiphertext = Arrays.copyOfRange(ciphertext, cipher.getBlockSize(), ciphertext.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            return cipher.doFinal(actualCiphertext);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting with DES", e);
        }
    }

    @Override
    public byte[] generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(DES_ALGORITHM);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating DES key", e);
        }
    }

    @Override
    public byte[] generateKey(byte[] seed) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(DES_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            keyGenerator.init(secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating DES key with seed", e);
        }
    }

    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return EncryptionAlgorithm.DES;
    }
}