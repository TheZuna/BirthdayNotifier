package hr.TheZuna.projekt.users;

import hr.TheZuna.projekt.controller.UserAuthenticationController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public sealed interface Md5Hashing permits UserAuthenticationController {
    default public String getHash(String password) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(password.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        System.out.println(hashtext);
        return hashtext;
    }
}
