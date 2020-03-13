package ro.blockchainpki.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Components {
    @Value("${csc.client.id}")
    private String clientId;
    @Value("${csc.client.secret}")
    private String clientSecret;


    @Bean
    public ro.blockchainpki.client.CSC.CSCAPIClient getClient() {
        return new ro.blockchainpki.client.CSC.CSCAPIClient(clientId, clientSecret);
    }

}
