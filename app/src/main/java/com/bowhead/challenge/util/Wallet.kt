package com.bowhead.challenge.util

import com.bowhead.challenge.api.Web3Client
//import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.web3j.crypto.WalletUtils
import org.web3j.tx.Transfer
import org.web3j.utils.Convert
import org.web3j.crypto.Credentials
import java.io.File
import java.math.BigDecimal
import java.security.Provider
import java.security.Security


class Wallet {
    companion object {
       // val initialTransaction = 1000 should test bigger amount to get more gas for quicker transactions
        val initialTransaction = 10000000

        fun createWallet(password: String, directory: String):Boolean {
            val file = File(directory)
            if (!file.mkdirs()) {
                file.mkdirs();
            }
            val Walletname = WalletUtils.generateLightNewWalletFile(password, file)
            PreferencesManager.savePref(PreferencesManager.keys.WALLET_DIR,file.toString() + "/" + Walletname)
            PreferencesManager.savePref(PreferencesManager.keys.PASS,password)
            val credentials = WalletUtils.loadCredentials(password, file.toString() + "/" + Walletname)
            return makeTransaction(initialTransaction.toDouble(), Convert.Unit.GWEI, getMasterAccount(), credentials)
        }

        fun getUserCredentials():Credentials{
            val dir=PreferencesManager.getPref<String>(PreferencesManager.keys.WALLET_DIR)
            val pass=PreferencesManager.getPref<String>(PreferencesManager.keys.PASS)
            return WalletUtils.loadCredentials(pass, dir)
        }

         private fun getMasterAccount(): Credentials {
            val credentials = Credentials.create("0x1f6f3640f0247be0127702d46ab855ecf41dbf48461c5059b2a5dcc5b78dc309")
            return credentials
        }


         private fun makeTransaction(amount: Double, unit: Convert.Unit, from: Credentials, to: Credentials):Boolean {

            val receipt = Transfer.sendFunds(
                    Web3Client.getWeb3j(),
                    getMasterAccount(),
                    to.address,
                    BigDecimal.valueOf(amount),
                    unit
            ).sendAsync()
            return receipt.get().isStatusOK
        }
    }
}