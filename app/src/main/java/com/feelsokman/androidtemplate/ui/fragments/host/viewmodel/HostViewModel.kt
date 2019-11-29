package com.feelsokman.androidtemplate.ui.fragments.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import com.feelsokman.androidtemplate.result.fold
import kotlinx.coroutines.launch
import timber.log.Timber

class HostViewModel(private val jsonPlaceHolderClient: JsonPlaceHolderClient) : ViewModel() {

    val textData = MutableLiveData<String>()

    fun getTodos() {
        viewModelScope.launch {
            jsonPlaceHolderClient.getTodo().fold(
                ifSuccess = {
                    textData.postValue(it.title)
                },
                ifError = {
                    textData.postValue(it.toString())
                }
            )
        }
    }

    override fun onCleared() {
        Timber.tag("NavigationLogger").d("HostViewModel cleared")
        super.onCleared()
    }
}
