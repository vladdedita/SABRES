package ro.blockchainpki.proxy.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class CSCAccessLogger extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610b57806100206000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c80638a26d96b1161005b5780638a26d96b146100e0578063c8a6bd2e14610100578063ca58aeca14610113578063cb9504c9146101265761007d565b80630505186c14610082578063159fc616146100ab5780634c500b5f146100cb575b600080fd5b61009561009036600461088c565b610139565b6040516100a29190610a76565b60405180910390f35b6100be6100b93660046108bf565b610218565b6040516100a29190610aec565b6100de6100d9366004610920565b6102d0565b005b6100f36100ee36600461088c565b6103d8565b6040516100a29190610ab9565b6100be61010e36600461088c565b6104f3565b6100be61012136600461088c565b61051f565b6100de610134366004610920565b610607565b60608060008360405161014c9190610a1b565b908152602001604051809103902080549050604051908082528060200260200182016040528015610187578160200160208202803883390190505b50905060005b60008460405161019d9190610a1b565b90815260405190819003602001902054811015610211576000846040516101c49190610a1b565b908152602001604051809103902081815481106101dd57fe5b9060005260206000209060040201600201600101548282815181106101fe57fe5b602090810291909101015260010161018d565b5092915050565b60008060028360405161022b9190610a1b565b908152602001604051809103902054905060008460405161024c9190610a1b565b9081526020016040518091039020818154811061026557fe5b906000526020600020906004020160020160010154600014156102895760006102c8565b6000846040516102999190610a1b565b908152602001604051809103902081815481106102b257fe5b9060005260206000209060040201600201600101545b949350505050565b600084511180156102e2575060008351115b80156102ef575060008251115b156103d25760405180606001604052808381526020018481526020016040518060400160405280428152602001848152508152506001856040516103339190610a1b565b9081526020016040518091039020600082015181600001908051906020019061035d929190610773565b5060208281015180516103769260018501920190610773565b506040919091015180516002830155602090810151600392830155815460018101835560009290925285516103d0927fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0191870190610773565b505b50505050565b60608060005b600354811015610211576104ab6001600383815481106103fa57fe5b906000526020600020016040516104119190610a6a565b9081526040805160209281900383018120805460026001821615610100026000190190911604601f810185900485028301850190935282825290929091908301828280156104a05780601f10610475576101008083540402835291602001916104a0565b820191906000526020600020905b81548152906001019060200180831161048357829003601f168201915b50505050508561071a565b156104eb5781600382815481106104be57fe5b906000526020600020016040516020016104d9929190610a37565b60405160208183030381529060405291505b6001016103de565b60006001826040516105059190610a1b565b908152604051908190036020019020600301549050919050565b6000806000836040516105329190610a1b565b908152604051908190036020019020541561056f5760016000846040516105599190610a1b565b9081526040519081900360200190205403610572565b60005b90506000836040516105849190610a1b565b9081526020016040518091039020818154811061059d57fe5b906000526020600020906004020160020160010154600014156105c1576000610600565b6000836040516105d19190610a1b565b908152602001604051809103902081815481106105ea57fe5b9060005260206000209060040201600201600101545b9392505050565b60008251118015610619575060008451115b8015610626575060008351115b156103d25760008460405161063b9190610a1b565b908152604080519182900360209081018320606084018352868452838201869052825180840184524281528083018690529284019290925281546001810183556000928352918190208351805160049094029091019261069e9284920190610773565b5060208281015180516106b79260018501920190610773565b506040918201518051600283015560200151600390910155516001906000906106e1908790610a1b565b908152602001604051809103902080549050036002836040516107049190610a1b565b9081526040519081900360200190205550505050565b60008160405160200161072d9190610a1b565b60405160208183030381529060405280519060200120836040516020016107549190610a1b565b6040516020818303038152906040528051906020012014905092915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106107b457805160ff19168380011785556107e1565b828001600101855582156107e1579182015b828111156107e15782518255916020019190600101906107c6565b506107ed9291506107f1565b5090565b61080b91905b808211156107ed57600081556001016107f7565b90565b600082601f83011261081e578081fd5b813567ffffffffffffffff80821115610835578283fd5b604051601f8301601f191681016020018281118282101715610855578485fd5b60405282815292508284830160200186101561087057600080fd5b8260208601602083013760006020848301015250505092915050565b60006020828403121561089d578081fd5b813567ffffffffffffffff8111156108b3578182fd5b6102c88482850161080e565b600080604083850312156108d1578081fd5b823567ffffffffffffffff808211156108e8578283fd5b6108f48683870161080e565b93506020850135915080821115610909578283fd5b506109168582860161080e565b9150509250929050565b60008060008060808587031215610935578182fd5b843567ffffffffffffffff8082111561094c578384fd5b6109588883890161080e565b9550602087013591508082111561096d578384fd5b6109798883890161080e565b9450604087013591508082111561098e578384fd5b5061099b8782880161080e565b949793965093946060013593505050565b6000815460018116600081146109c957600181146109e057610a13565b60ff198216855260028204607f1685019250610a13565b6002820484600052602060002060005b82811015610a0c578154888201526001909101906020016109f0565b5050850192505b505092915050565b60008251610a2d818460208701610af5565b9190910192915050565b60008351610a49818460208801610af5565b808301601760f91b8152610a6060018201866109ac565b9695505050505050565b600061060082846109ac565b602080825282518282018190526000918401906040840190835b81811015610aae578351835260209384019390920191600101610a90565b509095945050505050565b6000602082528251806020840152610ad8816040850160208701610af5565b601f01601f19169190910160400192915050565b90815260200190565b60005b83811015610b10578181015183820152602001610af8565b838111156103d2575050600091015256fea26469706673582212200f1f58767855550fa97a1b144c8bf737b74cf6f9d4bc2aa7ea368b1f4d8d20db64736f6c63430006010033";

    public static final String FUNC_GETALLACCESSREQUESTS = "getAllAccessRequests";

    public static final String FUNC_GETALLTX = "getAllTx";

    public static final String FUNC_GETLASTACCESSREQUEST = "getLastAccessRequest";

    public static final String FUNC_GETLASTACCESSREQUESTTX = "getLastAccessRequestTx";

    public static final String FUNC_GETSIGNREQUEST = "getSignRequest";

    public static final String FUNC_REGISTERACCESSREQUEST = "registerAccessRequest";

    public static final String FUNC_REGISTERSIGNREQUEST = "registerSignRequest";

    @Deprecated
    protected CSCAccessLogger(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CSCAccessLogger(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CSCAccessLogger(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CSCAccessLogger(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> getAllAccessRequests(String _credentialId) {
        final Function function = new Function(
                FUNC_GETALLACCESSREQUESTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_credentialId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getAllTx(String _credentialId) {
        final Function function = new Function(
                FUNC_GETALLTX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_credentialId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getLastAccessRequest(String _credentialID) {
        final Function function = new Function(
                FUNC_GETLASTACCESSREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_credentialID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getLastAccessRequestTx(String _credentialID, String _transactionID) {
        final Function function = new Function(
                FUNC_GETLASTACCESSREQUESTTX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_credentialID), 
                new org.web3j.abi.datatypes.Utf8String(_transactionID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getSignRequest(String _transactionID) {
        final Function function = new Function(
                FUNC_GETSIGNREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_transactionID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerAccessRequest(String _credentialID, String _hashes, String _transactionID, BigInteger _timestamp) {
        final Function function = new Function(
                FUNC_REGISTERACCESSREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_credentialID), 
                new org.web3j.abi.datatypes.Utf8String(_hashes), 
                new org.web3j.abi.datatypes.Utf8String(_transactionID), 
                new org.web3j.abi.datatypes.generated.Uint256(_timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> registerSignRequest(String _transactionID, String _signatures, String _credentialID, BigInteger _timestamp) {
        final Function function = new Function(
                FUNC_REGISTERSIGNREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_transactionID), 
                new org.web3j.abi.datatypes.Utf8String(_signatures), 
                new org.web3j.abi.datatypes.Utf8String(_credentialID), 
                new org.web3j.abi.datatypes.generated.Uint256(_timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CSCAccessLogger load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CSCAccessLogger(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CSCAccessLogger load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CSCAccessLogger(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CSCAccessLogger load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CSCAccessLogger(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CSCAccessLogger load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CSCAccessLogger(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CSCAccessLogger> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CSCAccessLogger.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CSCAccessLogger> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CSCAccessLogger.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CSCAccessLogger> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CSCAccessLogger.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CSCAccessLogger> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CSCAccessLogger.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
