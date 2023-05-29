package io.todimu.practice.studentportal.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KeyGenerator {

    private static final int LENGTH = 7;

    public static String random() {
        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            for (int i = 0; i < LENGTH; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedToken.toString();
    }
}
