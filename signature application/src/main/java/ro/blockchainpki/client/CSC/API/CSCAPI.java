package ro.blockchainpki.client.CSC.API;

import ro.blockchainpki.client.CSC.JSON_FORMATS.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CSCAPI {

    private static final String HTTP_POST = "POST";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private static final String HEADER_CONTENT_LENGTH = "Content-Length";
    private static final String HEADER_CONTENT_LANGUAGE = "Content-Language";
    private static final String CONTENT_LANGUAGE_EN_US = "en-US";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String AUTHORIZAITON_BEARER = "Bearer ";
//    private static final String BASE_URL = "https://msign-test.transsped.ro/csc/v0/";
    private static final String BASE_URL = "http://localhost:9090/csc/v0/";

    public static String requestOauth2Token(String code, String clientId, String clientSecret) throws IOException {
        String methodURL = BASE_URL + "oauth2/token";
        URL myURL = new URL(methodURL);


        String postData = "{ " +
                "\"grant_type\": \"authorization_code\", " +
                "\"code\": \"" + code + "\", " +
                "\"client_id\": \"" + clientId + "\", " +
                "\"client_secret\": \"" + clientSecret + "\", " +
                "\"redirect_uri\": \"http://localhost:8080/\"}";

        HttpURLConnection myURLConnection = buildPOSTRequest(myURL, postData);
        writeBodyToRequest(postData, myURLConnection);
        StringBuilder response = readResponseBody(myURLConnection);

        OAuth2TokenResponse oAuth2TokenResponse = new ObjectMapper().readValue(response.toString(), OAuth2TokenResponse.class);
        return oAuth2TokenResponse.getAccessToken();

    }


    public static CredentialsListResponse requestCredentialsList(String accessToken) throws IOException {
        String methodURL = BASE_URL + "credentials/list";
        URL myURL = new URL(methodURL);

        String bearerAuth = AUTHORIZAITON_BEARER + accessToken;
        String postData = "{ }";

        HttpURLConnection myURLConnection = buildPOSTRequestWithBearerAuth(myURL, bearerAuth, postData);

        writeBodyToRequest(postData, myURLConnection);

        StringBuilder response = readResponseBody(myURLConnection);

        CredentialsListResponse credentialsListResponse = new ObjectMapper().readValue(response.toString(), CredentialsListResponse.class);
        return credentialsListResponse;
    }

    public static CredentialsInfoResponse requestCredentialsInfo(String credentialId,String accessToken) throws IOException {
        String methodURL = BASE_URL + "credentials/info";
        URL myURL = new URL(methodURL);

        String bearerAuth = AUTHORIZAITON_BEARER + accessToken;
        String postData = "{ \"credentialID\": \"" + credentialId + "\",\"certInfo\": \"true\"}";

        HttpURLConnection myURLConnection = buildPOSTRequestWithBearerAuth(myURL, bearerAuth, postData);
        writeBodyToRequest(postData, myURLConnection);
        StringBuilder response = readResponseBody(myURLConnection);

        CredentialsInfoResponse credentialsInfoResponse = new ObjectMapper().readValue(response.toString(), CredentialsInfoResponse.class);
        return credentialsInfoResponse;
    }

    public static void credentialsSendOTP(String accessToken, String credentialId) throws IOException {
        String methodURL = BASE_URL + "credentials/sendOTP";
        URL myURL = new URL(methodURL);

        String bearerAuth = AUTHORIZAITON_BEARER + accessToken;
        String postData = "{ \"credentialID\": \"" + credentialId + "\"}";

        HttpURLConnection myURLConnection = buildPOSTRequestWithBearerAuth(myURL, bearerAuth, postData);
        writeBodyToRequest(postData, myURLConnection);
        StringBuilder response = readResponseBody(myURLConnection);
    }

    public static CredentialsAuthorizeResponse credentialsAuthorize(String base64Hash, String accessToken, String credentialId, String PIN, String OTP) throws IOException {
        System.out.println("AUTHORIZE HASH: " + base64Hash);

        String methodURL = BASE_URL + "credentials/authorize";
        URL myURL = new URL(methodURL);

        String bearerAuth = AUTHORIZAITON_BEARER + accessToken;
        String postData = "{ \"credentialID\": \"" + credentialId + "\", \"numSignatures\": \"1\", \"hash\": [\"" + base64Hash + "\"], \"PIN\": \"" + PIN + "\", \"OTP\": \"" + OTP + "\"  }";

        HttpURLConnection myURLConnection = buildPOSTRequestWithBearerAuth(myURL, bearerAuth, postData);

        writeBodyToRequest(postData, myURLConnection);

        StringBuilder response = readResponseBody(myURLConnection);
        CredentialsAuthorizeResponse resp = new ObjectMapper().readValue(response.toString(), CredentialsAuthorizeResponse.class);
        return resp;
    }

    public static String signatureSignHash(String base64Hash, String accessToken, String credentialId, String SAD) {
        try {
            String methodURL = BASE_URL + "signatures/signHash";
            URL myURL = new URL(methodURL);

            String bearerAuth = AUTHORIZAITON_BEARER + accessToken;
            String postData = "{ \"credentialID\": \"" + credentialId + "\", \"signAlgo\": \"1.2.840.113549.1.1.11\", \"hashAlgo\": \"2.16.840.1.101.3.4.2.1\", \"SAD\": \"" + SAD + "\", \"hash\": [\"" + base64Hash + "\"]}";

            HttpURLConnection myURLConnection = buildPOSTRequestWithBearerAuth(myURL, bearerAuth, postData);


            writeBodyToRequest(postData, myURLConnection);

            StringBuilder response = readResponseBody(myURLConnection);


            SignaturesSignHashResponse resp = new ObjectMapper().readValue(response.toString(), SignaturesSignHashResponse.class);
            return resp.signatures[0];
        } catch (Exception e) {
            System.out.println("Eroare la calcularea efectiva a semnaturii\n");
            e.printStackTrace();
            return null;
        }
    }
    public static String signatureSignHash(String base64Hash, String accessToken, String credentialId, String transactionId, String SAD) {
        try {
            String methodURL = BASE_URL + "signatures/signHash";
            URL myURL = new URL(methodURL);

            String bearerAuth = AUTHORIZAITON_BEARER + accessToken;
            String postData = "{ \"credentialID\": \"" + credentialId +
                    "\", \"signAlgo\": \"1.2.840.113549.1.1.11\", " +
                    "\"hashAlgo\": \"2.16.840.1.101.3.4.2.1\", " +
                    "\"SAD\": \"" + SAD + "\", " +
                    "\"hash\": [\"" + base64Hash + "\"], " +
                    "\"clientData\": \"" + transactionId +"\"}";

            HttpURLConnection myURLConnection = buildPOSTRequestWithBearerAuth(myURL, bearerAuth, postData);


            writeBodyToRequest(postData, myURLConnection);

            StringBuilder response = readResponseBody(myURLConnection);


            SignaturesSignHashResponse resp = new ObjectMapper().readValue(response.toString(), SignaturesSignHashResponse.class);
            return resp.signatures[0];
        } catch (Exception e) {
            System.out.println("Eroare la calcularea efectiva a semnaturii\n");
            e.printStackTrace();
            return null;
        }
    }

    public static String signaturesTimestamp(String base64Hash, String accessToken) {
        try {
            String methodURL = BASE_URL + "signatures/timestamp";
            URL myURL = new URL(methodURL);

            String bearerAuth = AUTHORIZAITON_BEARER + accessToken;
            String postData = "{  \"hash\":\"" + base64Hash + "\", \"hashAlgo\": \"2.16.840.1.101.3.4.2.1\" }";

            HttpURLConnection myURLConnection = buildPOSTRequestWithBearerAuth(myURL, bearerAuth, postData);


            writeBodyToRequest(postData, myURLConnection);

            StringBuilder response = readResponseBody(myURLConnection);

            SignaturesTimestampResponse resp = new ObjectMapper().readValue(response.toString(), SignaturesTimestampResponse.class);
            return resp.timestamp;
        } catch (Exception e) {
            return null;
        }
    }

    private static HttpURLConnection buildPOSTRequest(URL myURL, String postData) throws IOException {
        HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
        myURLConnection.setRequestProperty(HEADER_CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
        myURLConnection.setRequestMethod(HTTP_POST);
        myURLConnection.setRequestProperty(HEADER_CONTENT_LENGTH, "" + postData.getBytes().length);
        myURLConnection.setRequestProperty(HEADER_CONTENT_LANGUAGE, CONTENT_LANGUAGE_EN_US);
        myURLConnection.setUseCaches(false);
        myURLConnection.setDoInput(true);
        myURLConnection.setDoOutput(true);
        return myURLConnection;
    }

    private static HttpURLConnection buildPOSTRequestWithBearerAuth(URL myURL, String bearerAuth, String postData) throws IOException {
        HttpURLConnection myURLConnection = buildPOSTRequest(myURL, postData);
        myURLConnection.setRequestProperty(HEADER_AUTHORIZATION, bearerAuth);
        return myURLConnection;
    }

    private static StringBuilder readResponseBody(HttpURLConnection myURLConnection) throws IOException {
        InputStream is = myURLConnection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();

        return response;
    }

    private static void writeBodyToRequest(String postData, HttpURLConnection myURLConnection) throws IOException {
        DataOutputStream wr = new DataOutputStream(
                myURLConnection.getOutputStream());
        wr.writeBytes(postData);
        wr.close();
    }


}
