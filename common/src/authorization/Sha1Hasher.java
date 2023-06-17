package authorization;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Hasher {
    private static final String algorithm = "SHA-1";
    public static String encrypt(String msg) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance(algorithm);
        byte[] encBytes = sha1.digest(msg.getBytes());
        StringBuilder encMsg = new StringBuilder();
        for(byte b : encBytes){
            encMsg.append(b);
        }
        return encMsg.toString();
    }
}
