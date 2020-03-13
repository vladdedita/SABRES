package ro.blockchainpki.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsInfoResponse;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsListResponse;
import ro.blockchainpki.client.service.CSCService;
import sun.plugin.javascript.ocx.JSObject;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/csc")
public class CSCController extends GenericController {

    private final CSCService service;

    @GetMapping("/credentials/list")
    public CredentialsListResponse getCredentialsList() {
        return service.getCredentialsList();
    }

    @GetMapping("/credentials/info/{credentialId}")
    public CredentialsInfoResponse getCredentialsInfo(@PathVariable String credentialId) {
        return service.getCredentialsInfo(credentialId);
    }

    @PutMapping("/credentials/sendOTP/{credentialId}")
    public void sendOTP(final @PathVariable String credentialId) {
        service.sendOTP(credentialId);
    }

//    @PostMapping("/credentials/authorize")
//    public CredentialsAuthorizeResponse authorizeCredentials(
//            @RequestPart("dto") String credentialsAuthorizeJSON,
//            final @RequestPart("file") MultipartFile pdf) throws JsonProcessingException {
//        return service.authorizeCredentials(
//                new ObjectMapper().readValue(credentialsAuthorizeJSON, CredentialsAuthorizeDTO.class),
//                pdf);
//    }

    @PostMapping("/signPDF")
    public void signPDF(final @RequestPart("credentialID") String credentialId,
                        final @RequestPart("OTP") String otp,
                        final @RequestPart("PIN") String pin,
                        final @RequestPart("file") MultipartFile pdf) {
        service.signPDF(credentialId, otp, pin, pdf);

    }

    private class ValidationResponse {
        private boolean valid;
        ValidationResponse(boolean isValid){
            this.valid=isValid;
        }
        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public boolean isValid() {
            return valid;
        }
    }

    @PostMapping("validate")
    public ValidationResponse validateSignature(final @RequestPart("file") MultipartFile pdf){
        return new ValidationResponse(service.validatePDFSignature(pdf));
    }si

}
