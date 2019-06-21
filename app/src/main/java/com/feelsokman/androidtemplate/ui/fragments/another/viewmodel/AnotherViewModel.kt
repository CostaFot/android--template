package com.feelsokman.androidtemplate.ui.fragments.another.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import timber.log.Timber
import java.util.UUID

class AnotherViewModel(
    private val scheduler: Scheduler
) : ViewModel() {

    val textData: MutableLiveData<String> = MutableLiveData<String>().apply { postValue("Another") }

    fun changeText() {
        textData.postValue(UUID.randomUUID().toString())
    }

    override fun onCleared() {
        Timber.tag("NavigationLogger").d("AnotherViewModel cleared")
        super.onCleared()
    }
}