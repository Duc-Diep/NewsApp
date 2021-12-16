package com.ducdiep.newsapp.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ducdiep.newsapp.repository.AppPreferences

class LockScreenViewModel(application: Application) : AndroidViewModel(application) {
    val context: Context = getApplication<Application>().applicationContext
    val stateCircle1 = MutableLiveData<Boolean>()
    val stateCircle2 = MutableLiveData<Boolean>()
    val stateCircle3 = MutableLiveData<Boolean>()
    val stateCircle4 = MutableLiveData<Boolean>()
    val isFirstTime = MutableLiveData<Boolean>()
    val isSuccess = MutableLiveData<Boolean>()
    val isCreate = MutableLiveData<Boolean>()

    var currentPosition = 1
    var enterValue = ""

    init {
        AppPreferences.init(context)
        isFirstTime.value = AppPreferences.isFirstTime
        resetState()
    }

    fun updateValue(value: String) {
        when (currentPosition) {
            1 -> {
                enterValue += value
                currentPosition++
                stateCircle1.value = true
            }
            2 -> {
                enterValue += value
                currentPosition++
                stateCircle2.value = true
            }
            3 -> {
                enterValue += value
                currentPosition++
                stateCircle3.value = true
            }
            4 -> {
                enterValue += value
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
        resetState()
    }
    fun resetState(){
        stateCircle1.value = false
        stateCircle2.value = false
        stateCircle3.value = false
        stateCircle4.value = false
    }

}