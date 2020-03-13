package ro.blockchainpki.client.CSC.JSON_FORMATS;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsInfoResponseOTP {

    private String presence;
    private String type;
    private String format;
    private String label;
    private String description;
    @JsonProperty("ID")
    private String id;
    private String provider;


}
