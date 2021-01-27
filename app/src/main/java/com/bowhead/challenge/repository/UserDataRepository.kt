package com.bowhead.challenge.repository

import com.bowhead.challenge.api.Web3Client
import com.bowhead.challenge.models.LogItem
import com.bowhead.challenge.util.Util
import com.bowhead.challenge.util.Wallet
import org.web3j.protocol.core.methods.response.TransactionReceipt
import java.math.BigInteger

class UserDataRepository() {

    fun addUserData(logItem: LogItem): TransactionReceipt? {
        val itemBytes= Util.serializeObject(logItem)
        return Web3Client.getContractWrapper(Wallet.getUserCredentials()).addHealthData(itemBytes.value).send()
    }

    fun registerUser(): TransactionReceipt? {
        return Web3Client.getContractWrapper(Wallet.getUserCredentials()).registerUser(BigInteger.valueOf(0)).send()
    }

    fun getHealthData()=Web3Client.getContractWrapper(Wallet.getUserCredentials()).healthData.send()
}