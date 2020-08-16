package com.feelsokman.androidtemplate.ui.fragments.host.di

import androidx.lifecycle.ViewModel
import com.feelsokman.androidtemplate.di.module.ViewModelKey
import com.feelsokman.androidtemplate.ui.fragments.host.viewmodel.HostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HostViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(HostViewModel::class)
    abstract fun bindHostViewModel(viewModel: HostViewModel): ViewModel

}
