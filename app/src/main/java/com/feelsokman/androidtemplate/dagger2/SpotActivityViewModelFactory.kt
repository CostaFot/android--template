package com.feelsokman.androidtemplate.dagger2

import android.app.Activity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class SpotActivityViewModelFactory(
    private val wrapper: Wrapper,
    activity: Activity
) : AbstractSavedStateViewModelFactory(activity as SavedStateRegistryOwner, null) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        @Suppress("UNCHECKED_CAST")

        return SpotActivityViewModel(wrapper, handle) as T
    }
}
