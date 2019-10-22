package com.feelsokman.androidtemplate.ui.fragments.host.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feelsokman.net.domain.JsonPlaceHolderClient

class HostViewModelFactory(private val jsonPlaceHolderClient: JsonPlaceHolderClient) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")

        return HostViewModel(jsonPlaceHolderClient) as T
    }
}