package com.feelsokman.androidtemplate.ui.activity.di

import com.feelsokman.androidtemplate.ui.activity.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    internal fun providesMainViewModelFactory() =
        MainViewModelFactory()
}