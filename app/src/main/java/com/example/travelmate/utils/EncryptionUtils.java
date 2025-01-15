package com.example.travelmate.utils;

import android.util.Base64;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class EncryptionUtils {

    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "YourSecretKey123".getBytes();

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(String valueToEnc, String salt) throws Exception {
        Key key = generateKeyWithSalt(salt);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        return Base64.encodeToString(encValue, Base64.DEFAULT);
    }

    public static String decrypt(String encryptedValue, String salt) throws Exception {
        Key key = generateKeyWithSalt(salt);
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decValue = Base64.decode(encryptedValue, Base64.DEFAULT);
        byte[] decValue1 = c.doFinal(decValue);
        return new String(decValue1);
    }

    private static Key generateKeyWithSalt(String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());
        byte[] key = new byte[32];
        System.arraycopy(md.digest(keyValue), 0, key, 0, key.length);
        return new SecretKeySpec(key, ALGORITHM);
    }
}