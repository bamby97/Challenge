package com.bowhead.challenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bowhead.challenge.api.Web3Client
import com.bowhead.challenge.models.LogItem
import com.bowhead.challenge.util.Resource
import com.bowhead.challenge.util.Util
import com.bowhead.challenge.util.Wallet
import kotlinx.coroutines.Dispatchers
import org.apache.commons.lang3.SerializationUtils
import java.lang.Exception
import java.math.BigInteger

class UserDataViewModel:ViewModel() {
    fun addUserData(logItem: LogItem)= liveData(Dispatchers.IO){
        emit(Resource.loading(data= null))
        try{
            val itemBytes= Util.serializeObject(logItem)
            val tr=Web3Client.getContractWrapper(Wallet.getUserCredentials()).addHealthData(itemBytes.value).send()
            emit(Resource.success(data= tr))
        }catch (e: Exception){
            emit(Resource.error(data = null, message =e.message ?:"Error ocurred"))
        }
    }

    fun registerUser()= liveData(Dispatchers.IO){
        emit(Resource.loading(data= null))
        try{
            val rc=Web3Client.getContractWrapper(Wallet.getUserCredentials())//.healthData
                .registerUser(BigInteger.valueOf(0))
            val tr=rc.send()
                  emit(Resource.success(data=  tr))
        }catch (e: Exception){
            emit(Resource.error(data = null, message =e.message ?:"Error ocurred"))
        }
    }

    fun getUserData()= liveData(Dispatchers.IO){
        emit(Resource.loading(data= null))
        try{
            emit(Resource.success(data= Web3Client.getContractWrapper(Wallet.getUserCredentials()).healthData.send()))
        }catch (e: Exception){
            emit(Resource.error(data = null, message =e.message ?:"Error ocurred"))
        }
    }
}