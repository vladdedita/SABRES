package ro.blockchainpki.proxy.service;

import ro.blockchainpki.proxy.model.CredentialsAuthorizePayload;
import ro.blockchainpki.proxy.model.SignHashPayload;

public interface BlockchainService {

    /**
     * This method uses the smart contract in order to
     * write the last access time of a user to CSC credentials
     * @param payload - object that holds the needed information
     */
    void writeCredentialAccess(CredentialsAuthorizePayload payload);
    /**
     * This method uses the smart contract in order to
     * link the user's access to the credentials
     * to the signature itself - this way the user is sure that the private key
     * was actually accessed
     * @param payload - object that holds the needed information
     */
    void writeSignRequest(SignHashPayload payload);

}
