package ro.blockchainpki.client.CSC.JSON_FORMATS;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SignaturesSignHashResponse {
    @JsonProperty("signatures")
    public String[] signatures;
}
