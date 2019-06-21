package com.feelsokman.androidtemplate.ui.fragments.another.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Scheduler

class AnotherViewModelFactory(
    private val scheduler: Scheduler
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")

        return AnotherViewModel(scheduler) as T
    }
}