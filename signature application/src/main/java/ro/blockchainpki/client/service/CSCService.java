package ro.blockchainpki.client.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.signatures.PdfPKCS7;
import com.itextpdf.signatures.SignatureUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.blockchainpki.client.CSC.API.CSCAPI;
import ro.blockchainpki.client.CSC.API.CSCSignature;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsInfoResponse;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsListResponse;

import java.security.Security;
import java.util.List;

import static ro.blockchainpki.client.utils.SessionUtils.*;

@Service
@RequiredArgsConstructor
public class CSCService {

    @Value("${csc.client.id}")
    private String clientId;
    @Value("${csc.client.secret}")
    private String clientSecret;

    @SneakyThrows
    public void requestToken(String code) {
        String token = CSCAPI.requestOauth2Token(code, clientId, clientSecret);
        putSessionValue(token);
        getResponse().sendRedirect("/index.html");
    }

    @SneakyThrows
    public void redirectToAuthorization() {
        getResponse().sendRedirect("https://msign-test.transsped.ro/csc/v0/oauth2/authorize?" +
                "response_type=code&" +
                "client_id=" + clientId + "&" +
                "redirect_uri=http://localhost:8080/&" +
                "scope=service&" +
                "lang=en-US&" +
                "state=" + Math.round(Math.random() * 1000000));
    }


    @SneakyThrows
    public CredentialsListResponse getCredentialsList() {
        return CSCAPI.requestCredentialsList(getSessionValue());
    }

    @SneakyThrows
    public CredentialsInfoResponse getCredentialsInfo(String credentialId) {
        return CSCAPI.requestCredentialsInfo(credentialId, getSessionValue());
    }

    @SneakyThrows
    public void sendOTP(String credentialId) {
        CSCAPI.credentialsSendOTP(getSessionValue(), credentialId);
    }

    public void signPDF(final String credentialID, final String otp, final String pin, final MultipartFile file) {
        CredentialsInfoResponse credentialsInfo = getCredentialsInfo(credentialID);
        credentialsInfo.setCredentialId(credentialID);
        CSCSignature.signPDFFile(getSessionValue(), credentialsInfo, pin, otp, file);
    }

    @SneakyThrows
    public boolean validatePDFSignature(MultipartFile pdf) {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(pdf.getInputStream()));
        if(Security.getProvider(new BouncyCastleProvider().getName()) == null)
            Security.addProvider(new BouncyCastleProvider());
        SignatureUtil signUtil = new SignatureUtil(pdfDoc);
        List<String> names = signUtil.getSignatureNames();
        boolean isValid = true;
        for (String name : names) {
            PdfPKCS7 pkcs7 = signUtil.verifySignature(name);
            isValid = isValid && pkcs7.verify();
        }
        pdfDoc.close();
        return isValid;
    }
}
