package ro.blockchainpki.client.CSC.JSON_FORMATS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsInfoResponseKey {
    private String[] algo;
    private String status;
    private int len;
    private String curve;
}
