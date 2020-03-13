# SABRES - Secure Auditing using Blockchain for Remote Electronic Signature

In order to run the solution the following steps should be followed:

## Proxy

The proxy is configured externally using the project's *application.properties* located in *proxy/src/main/resources*
As the configurations are self-explanatory, one should go over every configuration and replace the values.
After finishing the configuration, the project can be built and compiled (*mvn clean install*) and ran as a java jar.

## Signature application

The backend of the signature application should be configured in the same way as the proxy. The location of the configuration file is
*signature application/src/main/resources*. After this, the project should be built and compiled (*mvn clean install*) and ran as a java jar.

## Blockchain

Before running the proxy and the signature application you should have a public or development blockchain available.

You will need *npm* and *truffle* installed globally.

In the development process of this application we used **Ganache** (*https://www.trufflesuite.com/ganache*) in order to 
deploy a local development blockchain.

After deploying the blockchain, one should compile and deploy the smart contracts on the development blockchain.
The contracts are located in *signature application/src/main/ui/contracts*. 
The address of the blockchain should be specified in the
*truffle-config.js* file.
After this, inside the */src/main/ui/contracts* folder the following command should be run
in order to deploy the contracts: **truffle migrate --reset**
This will execute the migration contract and reset the state of the contract if it was previously deployed.

If all steps were executed correctly, both applications should behave correctly.




