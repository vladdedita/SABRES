package ro.blockchainpki.client.utils;

import com.itextpdf.io.codec.Base64;
import com.itextpdf.signatures.BouncyCastleDigest;

import java.security.MessageDigest;

public class HashUtils {

    public static final String HASH_ALGO = "SHA-256";

    public static String computeBase64Hash(byte[] message) {
        try {

            MessageDigest digest = MessageDigest.getInstance(HASH_ALGO);
            byte[] hashBytes = digest.digest(message);
            return Base64.encodeBytes(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
