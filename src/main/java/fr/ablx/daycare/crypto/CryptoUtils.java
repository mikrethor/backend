package fr.ablx.daycare.crypto;

import fr.ablx.daycare.jpa.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Thor on 2017-03-04.
 */
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


        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update((password + salt).getBytes());
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //TODO add handled exception
        }

        return null;


    }


}
