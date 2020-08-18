package com.feelsokman.androidtemplate.work

import androidx.work.DelegatingWorkerFactory
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TemplateWorkerFactory @Inject constructor(
    jsonPlaceHolderClient: JsonPlaceHolderClient
) : DelegatingWorkerFactory() {
    init {
        addFactory(DoSomethingWorkerFactory(jsonPlaceHolderClient))
    }
}
