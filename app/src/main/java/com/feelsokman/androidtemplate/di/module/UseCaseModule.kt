package com.feelsokman.androidtemplate.di.module

import com.feelsokman.androidtemplate.preferences.AppPreferences
import com.feelsokman.androidtemplate.usecase.GetStringFromStorageUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler

@Module
object UseCaseModule {

    @Provides
    internal fun providesGetStringFromStorageUseCase(
        scheduler: Scheduler,
        appPreferences: AppPreferences
    ): GetStringFromStorageUseCase = GetStringFromStorageUseCase(scheduler, appPreferences)
}
