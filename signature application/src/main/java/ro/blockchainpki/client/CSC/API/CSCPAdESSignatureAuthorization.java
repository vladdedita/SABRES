package ro.blockchainpki.client.CSC.API;

import com.itextpdf.io.codec.Base64;
import com.itextpdf.signatures.IExternalSignature;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsInfoResponse;
import ro.blockchainpki.client.utils.HashUtils;

import java.util.Arrays;

public class CSCPAdESSignatureAuthorization implements IExternalSignature {

    private String accessToken;
    private String encryptionAlgorithm;
    private String hashAlgorithm;
    private CredentialsInfoResponse credentialInfo;
    private String sad;
    private byte[] toHashBytes;

    public CSCPAdESSignatureAuthorization(String accessToken, CredentialsInfoResponse credentialInfo, String sad) {
        this(accessToken,
                credentialInfo,
                sad,
                "RSA",
                HashUtils.HASH_ALGO);
    }

    public CSCPAdESSignatureAuthorization(String accessToken,
                                          CredentialsInfoResponse credentialInfo,
                                          String sad,
                                          String encryptionAlgorithm,
                                          String hashAlgorithm) {
        this.accessToken = accessToken;
        this.credentialInfo = credentialInfo;
        this.encryptionAlgorithm = encryptionAlgorithm;
        this.hashAlgorithm = hashAlgorithm;
        this.sad = sad;
    }

    @Override
    public String getHashAlgorithm() {
        return HashUtils.HASH_ALGO;
    }

    @Override
    public String getEncryptionAlgorithm() {
        return this.encryptionAlgorithm;
    }

    @Override
    public byte[] sign(byte[] bytes) {

        toHashBytes = bytes;
//        String   SAD = CSCAPI.credentialsAuthorize(hashToBeSigned,this.accessToken,this.credentialInfo.getCredentialId(),this.pin,this.otp).SAD;

//        String hashToBeSigned = HashUtils.computeBase64Hash(bytes);
//        System.out.println("SIGN HASH: " + hashToBeSigned);
//        System.out.println("Bytes: " + Arrays.toString(bytes));
//        String signature = CSCAPI.signatureSignHash(
//                hashToBeSigned,
//                this.accessToken,
//                this.credentialInfo.getCredentialId(),
//                this.sad);

        return bytes;
    }

    public byte[] getToHashBytes() {
        return toHashBytes;
    }
}
