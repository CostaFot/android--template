package com.feelsokman.androidtemplate.ui.fragments.host.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import com.feelsokman.androidtemplate.ui.OnActivityResultProxy

class HostViewModelFactory(
    private val jsonPlaceHolderClient: JsonPlaceHolderClient,
    private val onActivityResultProxy: OnActivityResultProxy
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")

        return HostViewModel(jsonPlaceHolderClient, onActivityResultProxy) as T
    }
}
