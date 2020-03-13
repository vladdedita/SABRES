package ro.blockchainpki.client.CSC.API;

import com.itextpdf.io.codec.Base64;
import com.itextpdf.signatures.*;

import java.security.MessageDigest;


public class CSCTimestampClient implements ITSAClient{

    private String accessToken;
    private String hashAlgorithmName;

    public CSCTimestampClient(String AccessToken){
        this(AccessToken,"SHA-256");
    }

    public CSCTimestampClient(String AccessToken, String HashAlgorithmName)
    {
        this.accessToken = AccessToken;
        this.hashAlgorithmName = HashAlgorithmName;
    }

    public MessageDigest getMessageDigest()
    {
        try {
            return MessageDigest.getInstance(this.hashAlgorithmName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] getTimeStampToken(byte[] imprint)
    {
        try
        {
            String hashToBeTimestamped = Base64.encodeBytes(imprint);

            String base64Timestamp = CSCAPI.signaturesTimestamp(hashToBeTimestamped,this.accessToken);

            byte[] result = Base64.decode(base64Timestamp);

            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }

    public int getTokenSizeEstimate() {
        return 4096;
    }
}
