package com.bowhead.challenge.api


//import org.bouncycastle.jce.provider.BouncyCastleProvider
import android.util.Log
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService
import org.web3j.tx.ClientTransactionManager
import org.web3j.tx.TransactionManager
//import org.web3j.tx.gas.DefaultGasProvider
import java.math.BigInteger
import java.security.Provider
import java.security.Security
import org.web3j.protocol.infura.InfuraHttpService
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.gas.DefaultGasProvider


object Web3Client {
    private const val URL="https://rinkeby.infura.io/v3/b1e7407912744890b54e9dc336ae31c4"
    private const val CONTRACT_ADDRESS="0xc7c44c64DBddA29e2723FD1056ab876A54ef24CE"
    private val GAS_LIMIT= BigInteger.valueOf(10000000)
    private val GAS_PRICE= BigInteger.valueOf(4300000)
    private val web3:Web3j=Web3j.build(InfuraHttpService(URL))


    fun getWeb3j():Web3j{
        return web3
    }
    fun getContractWrapper(credentials: Credentials):ContractWrapper{
        try {
            //if the client version has an error the user will not gain access if successful the user will get connnected
            val clientVersion: Web3ClientVersion = web3.web3ClientVersion().sendAsync().get()
            if (!clientVersion.hasError()) {
                Log.d("Connected!", "we are connected to Eth")
            } else {
                Log.e("Error connecting to eth", clientVersion.error.message)
                throw java.lang.Exception(clientVersion.error.message)

            }
        } catch (e: Exception) {

        }
        //transactions take too long to be completed transaction manager set 120 seconds to maximum wait
        val sleepDuration = 15 * 1000
        val attempts = 8 // DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH = 40

        val transactionManager: TransactionManager = RawTransactionManager(web3, credentials, attempts, sleepDuration)
        return ContractWrapper.load(CONTRACT_ADDRESS,web3,transactionManager, GAS_PRICE, GAS_LIMIT)
                //.load(CONTRACT_ADDRESS, web3, credentials, DefaultGasProvider())
    }

}