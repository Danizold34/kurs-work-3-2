package com.nyha.webfinal.util;

import java.math.BigInteger;
import java.util.Base64;

/**
 * The utility is responsible for encrypting passwords
 *
 * @author Dovlet Jumaev
 */
public class PasswordEncryption {
    private PasswordEncryption() {
    }

    /**
     * Encrypts password
     *
     * @param unencrypted {@link String} password
     * @return {@link String} encrypted password
     */
    public static String encrypt(String unencrypted) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytesEncoded = encoder.encode(unencrypted.getBytes());
        BigInteger bigInt = new BigInteger(1, bytesEncoded);
        String encrypted = bigInt.toString(16);
        return encrypted;
    }
}
