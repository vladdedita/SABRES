package ro.blockchainpki.client.CSC.API;

import com.itextpdf.io.codec.Base64;
import com.itextpdf.signatures.IExternalSignature;
import lombok.SneakyThrows;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsInfoResponse;
import ro.blockchainpki.client.utils.HashUtils;
import ro.blockchainpki.client.utils.SessionUtils;

import java.util.Arrays;
import java.util.UUID;

public class CSCPAdESSignature implements IExternalSignature {

    private String accessToken;
    private String encryptionAlgorithm;
    private String hashAlgorithm;
    private CredentialsInfoResponse credentialInfo;
    private String pin;
    private String otp;

    public CSCPAdESSignature(String accessToken, CredentialsInfoResponse credentialInfo, String pin, String otp) {
        this(accessToken,
                credentialInfo,
                pin,
                otp,
                "RSA",
                HashUtils.HASH_ALGO);
    }

    public CSCPAdESSignature(String accessToken,
                             CredentialsInfoResponse credentialInfo,
                             String pin,
                             String otp,
                             String encryptionAlgorithm,
                             String hashAlgorithm) {
        this.accessToken = accessToken;
        this.credentialInfo = credentialInfo;
        this.encryptionAlgorithm = encryptionAlgorithm;
        this.hashAlgorithm = hashAlgorithm;
        this.pin=pin;
        this.otp=otp;
    }

    @Override
    public String getHashAlgorithm() {
        return HashUtils.HASH_ALGO;
    }

    @Override
    public String getEncryptionAlgorithm() {
        return this.encryptionAlgorithm;
    }

    @SneakyThrows
    @Override
    public byte[] sign(byte[] bytes) {

        String hashToBeSigned = HashUtils.computeBase64Hash(bytes);

        String sad = CSCAPI.credentialsAuthorize(
                hashToBeSigned,
                this.accessToken,
                this.credentialInfo.getCredentialId(),
                this.pin,
                this.otp)
                .getSad();

        SessionUtils.getResponse().setHeader("hash",hashToBeSigned);
        String transactionId = String.valueOf(UUID.randomUUID());
        SessionUtils.getResponse().setHeader("transactionid", transactionId);

        String signature = CSCAPI.signatureSignHash(
                hashToBeSigned,
                this.accessToken,
                this.credentialInfo.getCredentialId(),
                transactionId,
                sad);

        return Base64.decode(signature);
    }

}
