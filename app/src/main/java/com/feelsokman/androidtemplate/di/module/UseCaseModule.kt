package com.feelsokman.androidtemplate.di.module

import com.feelsokman.androidtemplate.usecase.GetStringFromStorageUseCase
import com.feelsokman.preferences.AppPreferences
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler

@Module
class UseCaseModule {
    @Provides
    internal fun providesGetStringFromStorageUseCase(
        scheduler: Scheduler,
        appPreferences: AppPreferences
    ): GetStringFromStorageUseCase = GetStringFromStorageUseCase(scheduler, appPreferences)
}
