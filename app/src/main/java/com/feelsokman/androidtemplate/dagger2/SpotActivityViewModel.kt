package com.feelsokman.androidtemplate.dagger2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.UUID

class SpotActivityViewModel(private val wrapper: Wrapper) : ViewModel() {

    val textData = MutableLiveData<String>().apply { postValue(UUID.randomUUID().toString()) }

    val wrapperData = MutableLiveData<String>().apply { value = wrapper.number.toString() }

    override fun onCleared() {
        super.onCleared()
    }
}
