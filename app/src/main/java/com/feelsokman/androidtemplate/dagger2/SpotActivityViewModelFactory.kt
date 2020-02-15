package com.feelsokman.androidtemplate.dagger2

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SpotActivityViewModelFactory(
    private val wrapper: Wrapper,
    spotActivity: SpotActivity
) : AbstractSavedStateViewModelFactory(spotActivity, null) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        @Suppress("UNCHECKED_CAST")

        return SpotActivityViewModel(wrapper, handle) as T
    }
}
