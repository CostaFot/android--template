package com.feelsokman.androidtemplate.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.feelsokman.androidtemplate.extensions.logDebug
import com.feelsokman.androidtemplate.extensions.logError
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import com.feelsokman.androidtemplate.result.fold

class DoSomethingWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val jsonPlaceHolderClient: JsonPlaceHolderClient
) : CoroutineWorker(appContext, workerParams) {


    override suspend fun doWork(): Result {
        jsonPlaceHolderClient.getTodo().fold(
            ifSuccess = {
                logDebug { it.title }
                return Result.success()
            },
            ifError = {
                logError { it.toString() }
                return Result.failure()
            }
        )
    }
}




