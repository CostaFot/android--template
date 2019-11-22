package com.feelsokman.androidtemplate.ui.fragments.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import com.feelsokman.androidtemplate.net.domain.model.DomainTodo
import kotlinx.coroutines.launch
import timber.log.Timber

class HostViewModel(private val jsonPlaceHolderClient: JsonPlaceHolderClient) : ViewModel() {

    val textData = MutableLiveData<String>()

    fun getTodos() {
        viewModelScope.launch {
            val domainTodo: DomainTodo? = jsonPlaceHolderClient.getTodos()
            textData.postValue(domainTodo?.title)
        }
    }

    override fun onCleared() {
        Timber.tag("NavigationLogger").d("HostViewModel cleared")
        super.onCleared()
    }
}
