package com.feelsokman.androidtemplate.ui.fragments.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import com.feelsokman.androidtemplate.result.fold
import com.feelsokman.androidtemplate.ui.OnActivityResultProxy
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class HostViewModel(
    private val jsonPlaceHolderClient: JsonPlaceHolderClient,
    private val onActivityResultProxy: OnActivityResultProxy
) : ViewModel() {

    val textData = MutableLiveData<String>()

    @FlowPreview
    fun getTodos() {
        viewModelScope.launch {
            onActivityResultProxy.getStringFromActivityResult().collect { result ->
                result.fold(
                    ifSuccess = {
                        Timber.d(it)
                    },
                    ifError = {
                        Timber.d(it.localizedMessage)
                    }
                )
            }
        }
    }

    override fun onCleared() {
        Timber.tag("NavigationLogger").d("HostViewModel cleared")
        super.onCleared()
    }
}
