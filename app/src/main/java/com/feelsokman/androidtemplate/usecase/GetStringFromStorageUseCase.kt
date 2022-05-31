package com.feelsokman.androidtemplate.usecase

import com.feelsokman.androidtemplate.preferences.AppPreferences

class GetStringFromStorageUseCase(
    private val appPreferences: AppPreferences
) {

    fun getStringFromStorage() {
    }

    private fun accessStorage(appPreferences: AppPreferences): String {
        return appPreferences.getSampleString()
    }
}
