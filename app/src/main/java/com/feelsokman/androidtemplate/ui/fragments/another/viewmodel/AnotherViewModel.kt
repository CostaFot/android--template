package com.feelsokman.androidtemplate.ui.fragments.another.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feelsokman.androidtemplate.usecase.GetStringFromStorageUseCase
import timber.log.Timber
import javax.inject.Inject

class AnotherViewModel @Inject constructor(
    private val getStringFromStorageUseCase: GetStringFromStorageUseCase
) : ViewModel() {

    val textData = MutableLiveData<String>()

    fun observeStringFromStorage() {
    }

    override fun onCleared() {
        Timber.tag("NavigationLogger").d("AnotherViewModel onCleared")
        super.onCleared()
    }
}
