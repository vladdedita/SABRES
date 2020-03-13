package ro.blockchainpki.proxy.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import ro.blockchainpki.proxy.contracts.CSCAccessLogger;
import ro.blockchainpki.proxy.model.CredentialsAuthorizePayload;
import ro.blockchainpki.proxy.model.SignHashPayload;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlockchainServiceImpl implements BlockchainService {

    private final CSCAccessLogger contract;

    @SneakyThrows
    public void writeCredentialAccess(CredentialsAuthorizePayload payload) {
        long currentTime = new Date().getTime();
        String currentTimeString = String.valueOf(currentTime);
        UUID uuid = UUID.randomUUID();
        TransactionReceipt call = contract.registerAccessRequest(
                payload.getCredentialId(),
                payload.getHash()[0],
                uuid.toString(),
                new BigInteger(currentTimeString)).send();

        System.out.println("\t\t\tUSED UUID: " + uuid);

        System.out.println("Contract call: \n" + call.toString());
    }

    @SneakyThrows
    public void writeSignRequest(SignHashPayload payload) {
        long currentTime = new Date().getTime();
        String currentTimeString = String.valueOf(currentTime);
        String transactionID = payload.getClientData();
        String signature = payload.getSignatures().get(0);
        BigInteger timestamp = new BigInteger(currentTimeString);
        log.info("BLOCKCHAIN: Sending registerSignRequest(\" "+transactionID+"\", \""+signature+" \", \" "+timestamp+" \"");
        TransactionReceipt call = contract.registerSignRequest(
                transactionID,
                signature,
                payload.getCredentialId(),
                timestamp).send();

        log.info("Contract call: \n" + call.toString());
    }

}
