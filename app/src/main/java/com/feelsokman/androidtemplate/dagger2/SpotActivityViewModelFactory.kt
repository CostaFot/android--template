package com.feelsokman.androidtemplate.dagger2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SpotActivityViewModelFactory(private val wrapper: Wrapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")

        return SpotActivityViewModel(wrapper) as T
    }
}
