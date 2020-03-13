package ro.blockchainpki.client.CSC.JSON_FORMATS;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SignaturesTimestampResponse {
    @JsonProperty("timestamp")
    public String timestamp;
}
