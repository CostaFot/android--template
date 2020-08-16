package com.feelsokman.androidtemplate.di.module

import androidx.lifecycle.ViewModelProvider
import com.feelsokman.androidtemplate.utilities.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
@Suppress("UNUSED")
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
