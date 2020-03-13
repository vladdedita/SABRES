package ro.blockchainpki.proxy.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import ro.blockchainpki.proxy.contracts.CSCAccessLogger;

@Slf4j
@Configuration
public class BlockchainConfig {

    @Value("${blockchain.account.pkey}")
    private String accountPrivateKey;

    @Value("${blockchain.contract.address}")
    private String contractAddress;

    @Value("${blockchain.url}")
    private String blockchainUrl;

    @SneakyThrows
    @Bean
    public Web3j connectToBlockchain(){
        Web3j web3 = Web3j.build(new HttpService(blockchainUrl));
        // web3_clientVersion returns the current client version.
        Web3ClientVersion clientVersion = web3.web3ClientVersion().send();

        // eth_blockNumber returns the number of most recent block.
        EthBlockNumber blockNumber = web3.ethBlockNumber().send();

        // eth_gasPrice, returns the current price per gas in wei.
        EthGasPrice gasPrice = web3.ethGasPrice().send();

        log.info("Client version: " + clientVersion.getWeb3ClientVersion());
        log.info("Block number: " + blockNumber.getBlockNumber());
        log.info("Gas price: " + gasPrice.getGasPrice());

        return web3;
    }
    @Bean
    public Credentials createCredentials() {
        return Credentials.create(accountPrivateKey);
    }

    @Bean
    public CSCAccessLogger loadContract(){
        return CSCAccessLogger.load(contractAddress, connectToBlockchain(), createCredentials(), new DefaultGasProvider());
    }
}
