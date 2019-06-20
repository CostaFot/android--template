package com.feelsokman.androidtemplate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class MainViewModel : ViewModel() {

    val visibilityOfKeyboardData = MutableLiveData<Boolean>().apply { postValue(false) }

    override fun onCleared() {
        Timber.d("MainViewModel cleared")
        super.onCleared()
    }

    fun attemptToHideKeyboard(flag: Boolean) {
        visibilityOfKeyboardData.postValue(flag)
    }
}