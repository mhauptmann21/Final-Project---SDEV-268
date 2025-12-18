package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityModule {

    public static String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());

            byte[] digest = md.digest();

            // Convert to hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(input == null) {
            throw new IllegalArgumentException("Password cannot be null.");
        }
        
        return null;
    }
    
}
