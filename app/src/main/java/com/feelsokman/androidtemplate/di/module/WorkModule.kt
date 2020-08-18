package com.feelsokman.androidtemplate.di.module

import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import com.feelsokman.androidtemplate.core.features.FlagProvider
import com.feelsokman.androidtemplate.work.TemplateWorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object WorkModule {

    @Provides
    @Singleton
    internal fun providesWorkManager(context: Context): WorkManager = WorkManager.getInstance(context)

    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
        myWorkerFactory: TemplateWorkerFactory,
        flagProvider: FlagProvider
    ): Configuration {
        return Configuration.Builder().apply {
            if (flagProvider.isDebugEnabled) {
                setMinimumLoggingLevel(android.util.Log.DEBUG)
            }
            setWorkerFactory(myWorkerFactory)
        }.build()
    }
}
