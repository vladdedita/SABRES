package ro.blockchainpki.client.CSC;

import ro.blockchainpki.client.CSC.API.CSCAPI;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsAuthorizeResponse;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsInfoResponse;
import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsListResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class CSCAPIClient {

    private String authorizationCode;
    private String accessToken;

    private String clientId;
    private String clientSecret;

    private CSCAPI api;

    public CSCAPIClient(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }


    public String requestAccessToken(String authorizationCode) {
        if (this.accessToken != null && !this.accessToken.isEmpty())
            return this.accessToken;

        try {
            this.accessToken = CSCAPI.requestOauth2Token(authorizationCode, this.clientId, this.clientSecret);
        } catch (Exception e) {
            System.out.println("Encountered error. The authorization process will start over.");
//            this.authorizationCode = null;
//            requestAccessToken();
        }
        return this.accessToken;
    }


    public CredentialsListResponse requestCredentialsList(String token) {
        try {
            return CSCAPI.requestCredentialsList(token);
        } catch (IOException e) {
            //If the requests fails, it's possibly because of the expired access token
            System.out.println("Access token expired. Will retry authorization process...");
//            retryAuthorization();
        }
        return null;
    }

    public CredentialsInfoResponse requestCredentialsInfo(String credentialId, String accessToken) {
        try {
            return CSCAPI.requestCredentialsInfo(accessToken, credentialId);
        } catch (IOException e) {
            //If the requests fails, it's possibly because of the expired access token
            System.out.println("Access token expired. Will retry authorization process...");
            retryAuthorization();
        }
        return null;
    }



    private String requestUserOTP() {
        System.out.println("Please enter OTP: ");
        return getUserInputString();
    }

    private void retryAuthorization() {
        this.accessToken = null;
//        requestAccessToken();
    }

    private void requestUsersAuthorizationCode() {
        if (authorizationCode == null || authorizationCode.isEmpty()) {
            System.out.println("Please enter your authorization code:");
            this.authorizationCode = getUserInputString();
        }
    }

    @SneakyThrows
    private static String getUserInputString() {
        return new Scanner(System.in).nextLine();
    }

}
