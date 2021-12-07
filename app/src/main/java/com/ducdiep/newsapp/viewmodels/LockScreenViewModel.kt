package com.ducdiep.newsapp.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ducdiep.newsapp.repository.AppPreferences

class LockScreenViewModel(application: Application) : AndroidViewModel(application) {
    var context: Context = getApplication<Application>().applicationContext
    var stateCircle1 = MutableLiveData<Boolean>()
    var stateCircle2 = MutableLiveData<Boolean>()
    var stateCircle3 = MutableLiveData<Boolean>()
    var stateCircle4 = MutableLiveData<Boolean>()
    var isFirstTime = MutableLiveData<Boolean>()
    var isSuccess = MutableLiveData<Boolean>()
    var isCreate = MutableLiveData<Boolean>()

    var currentPosition = 1
    var enterValue = ""

    init {
        AppPreferences.init(context)
        isFirstTime.value = AppPreferences.isFirstTime
        stateCircle1.value = false
        stateCircle2.value = false
        stateCircle3.value = false
        stateCircle4.value = false
    }

    fun updateValue(value: String) {
        when (currentPosition) {
            1 -> {
                enterValue += value
                Log.d("Pass", "createOrCheckPassword: $enterValue")
                currentPosition++
                stateCircle1.value = true
            }
            2 -> {
                enterValue += value
                Log.d("Pass", "createOrCheckPassword: $enterValue")
                currentPosition++
                stateCircle2.value = true
            }
            3 -> {
                enterValue += value
                Log.d("Pass", "createOrCheckPassword: $enterValue")
                currentPosition++
                stateCircle3.value = true
            }
            4 -> {
                enterValue += value
                Log.d("Pass", "createOrCheckPassword: $enterValue")
                stateCircle4.value = true
                createOrCheckPassword()
                enterValue = ""
            }
            else ->{

            }
        }
    }

    private fun createOrCheckPassword() {
        if (isFirstTime.value == true) {
            isCreate.value = true
            Log.d("Pass", "createOrCheckPassword: $enterValue")
            AppPreferences.password = enterValue
            AppPreferences.isFirstTime = false
            isFirstTime.value = false
        } else {
            if (enterValue == AppPreferences.password) {
                isSuccess.value = true
            } else {
                isSuccess.value = false
            }
        }
        resetData()
    }

    fun resetData() {
        currentPosition = 1
        stateCircle1.value = false
        stateCircle2.value = false
        stateCircle3.value = false
        stateCircle4.value = false
    }

}