package ro.blockchainpki.client.CSC.JSON_FORMATS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CredentialsInfoResponse {
    @JsonIgnore
    private String credentialId;

    // @JsonProperty("authMode")
    private String authMode = null;

    // @JsonProperty("cert")
    private CredentialsInfoResponseCertificate cert;

    // @JsonProperty("multisign")
    private Boolean multisign = false;

    @JsonProperty("key")
    private CredentialsInfoResponseKey key;
    private String description;
    private String lang;

    @JsonProperty("SCAL")
    private String scal;

    @JsonProperty("PIN")
    private CredentialsInfoResponsePIN pin;

    @JsonProperty("OTP")
    private CredentialsInfoResponseOTP otp;

    public CredentialsInfoResponse() {
        authMode = null;
        cert = new CredentialsInfoResponseCertificate();
        multisign = false;
        key = new CredentialsInfoResponseKey();
    }

}

