package com.feelsokman.androidtemplate.di.module

import com.feelsokman.androidtemplate.preferences.AppPreferences
import com.feelsokman.androidtemplate.usecase.GetStringFromStorageUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    internal fun providesGetStringFromStorageUseCase(
        appPreferences: AppPreferences
    ): GetStringFromStorageUseCase = GetStringFromStorageUseCase(appPreferences)
}
