package com.feelsokman.androidtemplate.ui.activity.di

import androidx.lifecycle.ViewModel
import com.feelsokman.androidtemplate.di.module.ViewModelKey
import com.feelsokman.androidtemplate.ui.activity.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
