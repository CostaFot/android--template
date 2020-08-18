package com.feelsokman.androidtemplate.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient

class DoSomethingWorkerFactory(
    private val jsonPlaceHolderClient: JsonPlaceHolderClient
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when (workerClassName) {
            DoSomethingWorker::class.java.name ->
                DoSomethingWorker(appContext, workerParameters, jsonPlaceHolderClient)
            else ->
                // Return null, so that the base class can delegate to the default WorkerFactory.
                null
        }
    }
}
