package com.feelsokman.androidtemplate

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.feelsokman.androidtemplate.core.initialize.AppInitializer
import com.feelsokman.androidtemplate.di.HasComponent
import com.feelsokman.androidtemplate.di.component.AppComponent
import javax.inject.Inject

class TemplateApplication : Application(), HasComponent<AppComponent> {

    @Inject lateinit var appInitializer: AppInitializer

    override fun onCreate() {
        super.onCreate()
        initialiseDagger()
        appInitializer.startup(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private fun initialiseDagger() {
        AppComponent.initAppComponent(this)
    }

    override val component: AppComponent by lazy {
        AppComponent.instance
    }
}
