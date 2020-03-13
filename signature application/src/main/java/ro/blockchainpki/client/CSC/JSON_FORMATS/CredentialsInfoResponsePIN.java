package ro.blockchainpki.client.CSC.JSON_FORMATS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsInfoResponsePIN {

    private String presence;
    private String format;
    private String label;
    private String description;


}
