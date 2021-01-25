package com.bowhead.challenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bowhead.challenge.util.Resource
import com.bowhead.challenge.util.Wallet
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.lang.Exception

class UserAccountViewModel:ViewModel() {
    fun generateWallet(password:String, dir: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data= null))
        try{
            emit(Resource.success(data= Wallet.createWallet(password,dir)))
        }catch (e: Exception){
            emit(Resource.error(data = null, message =e.message ?:"Error ocurred"))
        }
    }
}