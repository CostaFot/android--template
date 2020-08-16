package com.feelsokman.androidtemplate.ui.fragments.another.di

import androidx.lifecycle.ViewModel
import com.feelsokman.androidtemplate.di.module.ViewModelKey
import com.feelsokman.androidtemplate.ui.fragments.another.viewmodel.AnotherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AnotherViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnotherViewModel::class)
    abstract fun bindAnotherViewModel(viewModel: AnotherViewModel): ViewModel
}
