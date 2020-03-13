package ro.blockchainpki.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsAuthorizeDTO {
    private String credentialId;
    private String pin;
    private String otp;
}
