package utils;

import java.security.SecureRandom;

public class RandomUtils {
    public static String getRandomString(int len) {

        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom rnd = new SecureRandom();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < len; i++)
            result.append(AB.charAt(rnd.nextInt(AB.length())));

        return result.toString();
    }
    public static String incorrectRandomEmail() {
        return getRandomString(10) + "@qa";
    }
    public static String shortRandomPassword() {
        return getRandomString(5);
    }
}
