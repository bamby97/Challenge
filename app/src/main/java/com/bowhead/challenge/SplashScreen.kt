package com.bowhead.challenge

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bowhead.challenge.util.PreferencesManager
import com.bowhead.challenge.util.Wallet
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.lang.Exception
import java.security.Provider
import java.security.Security


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //check if first time running app
        setupBouncyCastle()
        PreferencesManager.init(applicationContext)
        var userHaveWallet:Boolean
        try {
            Wallet.getUserCredentials()
            userHaveWallet=true
        }catch (e:Exception){
            userHaveWallet=false
        }
        val intent= if(userHaveWallet) Intent(this,MainActivity::class.java) else Intent(this,IntroActivity::class.java)
        startActivity(intent)
        finishAfterTransition()
    }


    private fun setupBouncyCastle() {
        val provider: Provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)
            ?: // Web3j will set up a provider  when it's used for the first time.
            return
        if (provider.javaClass.equals(BouncyCastleProvider::class.java)) {
            return
        }
        //There is a possibility  the bouncy castle registered by android may not have all ciphers
        //so we  substitute with the one bundled in the app.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME)
        Security.insertProviderAt(BouncyCastleProvider(), 1)
    }
}