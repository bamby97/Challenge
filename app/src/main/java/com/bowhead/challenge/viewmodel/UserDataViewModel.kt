package com.bowhead.challenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bowhead.challenge.api.Web3Client
import com.bowhead.challenge.models.LogItem
import com.bowhead.challenge.repository.UserDataRepository
import com.bowhead.challenge.util.Resource
import com.bowhead.challenge.util.Util
import com.bowhead.challenge.util.Wallet
import kotlinx.coroutines.Dispatchers
import org.apache.commons.lang3.SerializationUtils
import java.lang.Exception
import java.math.BigInteger

class UserDataViewModel:ViewModel() {
    val repository:UserDataRepository=UserDataRepository()

    fun addUserData(logItem: LogItem)= liveData(Dispatchers.IO){
        emit(Resource.loading(data= null))
        try{
            emit(Resource.success(data= repository.addUserData(logItem)))
        }catch (e: Exception){
            emit(Resource.error(data = null, message =e.message ?:"Error ocurred"))
        }
    }

    fun registerUser()= liveData(Dispatchers.IO){
        emit(Resource.loading(data= null))
        try{
                        emit(Resource.success(data=  repository.registerUser()))
        }catch (e: Exception){
            emit(Resource.error(data = null, message =e.message ?:"Error ocurred"))
        }
    }

    fun getUserData()= liveData(Dispatchers.IO){
        emit(Resource.loading(data= null))
        try{
            emit(Resource.success(data= repository.getHealthData()))
        }catch (e: Exception){
            emit(Resource.error(data = null, message =e.message ?:"Error ocurred"))
        }
    }
}