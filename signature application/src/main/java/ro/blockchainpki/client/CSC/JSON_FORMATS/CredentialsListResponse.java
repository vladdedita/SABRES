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
public class CredentialsListResponse {
    @JsonProperty("credentialIDs")
    private String[] credentialIds;

}
