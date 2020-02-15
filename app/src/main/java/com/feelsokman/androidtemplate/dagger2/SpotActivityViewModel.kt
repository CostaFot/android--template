package com.feelsokman.androidtemplate.dagger2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.UUID

class SpotActivityViewModel(
    private val wrapper: Wrapper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val textData = MutableLiveData<String>().apply { postValue(UUID.randomUUID().toString()) }

    val wrapperData = MutableLiveData<String>()

    init {
        wrapperData.value = savedStateHandle.get("balls") ?: "null"
    }

    fun go() {
        viewModelScope.launch {
            wrapperData.value = UUID.randomUUID().toString().apply { savedStateHandle.set("balls", this) }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
