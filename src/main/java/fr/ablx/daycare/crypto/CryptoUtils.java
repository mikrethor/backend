package fr.ablx.daycare.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CryptoUtils {

    private static CryptoUtils INSTANCE;

    private CryptoUtils() {

    }

    public static CryptoUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CryptoUtils();
        }
        return INSTANCE;
    }

    public String getSalt(int size) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return new String(bytes);
    }

    public String encryption(String password, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update((password + salt).getBytes());
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //TODO add handled exception
        }

        return null;


    }


}
