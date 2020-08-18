package com.feelsokman.androidtemplate.core.initialize

import android.app.Application
import com.facebook.stetho.Stetho
import com.feelsokman.androidtemplate.core.features.FlagProvider
import com.feelsokman.androidtemplate.extensions.logDebug
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject


class AppInitializer @Inject constructor(
    private val featureFlagProvider: FlagProvider
) {

    private var isInitialized = AtomicBoolean(false)

    fun startup(application: Application) {

        check(!isInitialized.get()) { "Attempted to initialize app more than once" }

        initTimber()
        initStetho(application)

        isInitialized.set(true)
    }

    private fun initTimber() {
        if (featureFlagProvider.isDebugEnabled) {
            Timber.plant(Timber.DebugTree())
            logDebug { "Timber initialised" }
        }
    }

    private fun initStetho(application: Application) {
        if (featureFlagProvider.isDebugEnabled) {
            Stetho.initializeWithDefaults(application)
            logDebug { "Stetho initialized" }
        }
    }

}
