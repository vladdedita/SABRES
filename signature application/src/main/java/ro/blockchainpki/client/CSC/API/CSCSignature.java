package ro.blockchainpki.client.CSC.API;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.signatures.*;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsInfoResponse;
import ro.blockchainpki.client.utils.SessionUtils;

import javax.servlet.http.HttpServletResponse;
import java.security.cert.Certificate;
import java.util.ArrayList;

public class CSCSignature {

    @SneakyThrows
    public static void signPDFFile(String accessToken, CredentialsInfoResponse info, String pin, String otp, MultipartFile file) {
        try (PdfReader reader = new PdfReader(file.getInputStream())
        ) {

            HttpServletResponse response = SessionUtils.getResponse();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getOriginalFilename() + "_signed.pdf");


            PdfSigner signer = new PdfSigner(reader, response.getOutputStream(), false);
            setSignatureAppeareance(signer);

            signer.setFieldName("sig");

            IExternalSignature pks = new CSCPAdESSignature(accessToken, info, pin,otp);
            IExternalDigest digest = new BouncyCastleDigest();
            //Use TSAClient if you need to attach timestamp to your signature.
            //Keep in mind that timestamps might be billed.
            //ITSAClient itsaClient = new CSC.CSCTimestampClient(accessToken);

            Certificate[] chain = CSCAPIUtils.GetCertChain(accessToken, info);

            final ICrlClient signingCertCrl = new CrlClientOnline(chain);

            ArrayList<ICrlClient> crlList = new ArrayList<ICrlClient>();
            crlList.add(signingCertCrl);

            signer.signDetached(digest, pks, chain, crlList, null, null, 0, PdfSigner.CryptoStandard.CADES);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionUtils.getResponse().flushBuffer();
        }


    }

    private static void setSignatureAppeareance(PdfSigner signer) {
        PdfSignatureAppearance appearance = signer.getSignatureAppearance()
                .setReason("Testing purpose")
                .setLocation("Bucharest, RO")
                .setReuseAppearance(false);
        Rectangle rect = new Rectangle(36, 200, 800, 600);
        appearance.setPageRect(rect).setPageNumber(1);
    }
}
