package ro.blockchainpki.proxy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CredentialsAuthorizePayload {
    /**
     * Required
     */
    @JsonProperty("credentialID")
    String credentialId;
    /**
     * Required
     */
    @JsonProperty("numSignatures")
    Long numSignatures;
    /**
     *     REQUIRED Conditional
     *     For SCAL 2 - required - number of hashes in array should correspond to numSignatures
     */
    @JsonProperty("hash")
    String[] hash;
    /**
     * Required Conditional
     * credentials/info - authMode = explicit and PIN/presence not false
     */
    @JsonProperty("PIN")
    String pin;
    /**
     * Required Conditional
     * credentials/info - authMode = explicit and OTP/presence not false
     */
    @JsonProperty("OTP")
    String otp;
}
