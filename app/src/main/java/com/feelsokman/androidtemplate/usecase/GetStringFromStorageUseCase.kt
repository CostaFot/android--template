package com.feelsokman.androidtemplate.usecase

import com.feelsokman.storage.Storage
import io.reactivex.Scheduler

class GetStringFromStorageUseCase(private val scheduler: Scheduler, private val storage: Storage) {

    fun getStringFromStorage() {
    }

    private fun accessStorage(storage: Storage): String {
        return storage.getSampleString()
    }
}