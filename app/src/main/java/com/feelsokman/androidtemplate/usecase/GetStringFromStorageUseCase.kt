package com.feelsokman.androidtemplate.usecase

import com.feelsokman.androidtemplate.preferences.AppPreferences
import io.reactivex.Scheduler

class GetStringFromStorageUseCase(
    private val scheduler: Scheduler,
    private val appPreferences: AppPreferences
) {

    fun getStringFromStorage() {
    }

    private fun accessStorage(appPreferences: AppPreferences): String {
        return appPreferences.getSampleString()
    }
}
