package com.feelsokman.androidtemplate.ui.fragments.host.di

import com.feelsokman.androidtemplate.ui.fragments.host.viewmodel.HostViewModelFactory
import com.feelsokman.net.domain.JsonPlaceHolderClient
import dagger.Module
import dagger.Provides

@Module
class HostModule {

    @Provides
    internal fun providesHostViewModelFactory(jsonPlaceHolderClient: JsonPlaceHolderClient) =
        HostViewModelFactory(jsonPlaceHolderClient)
}
