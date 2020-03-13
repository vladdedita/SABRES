package ro.blockchainpki.proxy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignHashPayload {
    /**
     * Required
     */
    @JsonProperty("credentialID")
    String credentialId;
    /**
     * Required
     */
    @JsonProperty("SAD")
    String sad;
    /**
     * Required
     */
    @JsonProperty("hash")
    String[] hash;
    /**
     * Required Conditional
     * if not implicitly specified by the signAlgo algorithm
     */
    @JsonProperty("hashAlgo")
    String hashAlgo;
    /**
     * Required
     */
    @JsonProperty("signAlgo")
    String signAlgo;
    /**
     * Required Conditional
     * if required by the signature algorithm
     */
    @JsonProperty("signAlgoParams")
    String signAlgoParams;

    /**
     * OPTIONAL
     * @implNote This will hold the random value generated at credentials/authorize in order to identify the transaction
     */
    @JsonProperty("clientData")
    String clientData;

    /**
     * Required
     * Should be present in signatures/signHash response
     */
    @JsonIgnore
    List<String> signatures;

}

