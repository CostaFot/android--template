package com.feelsokman.androidtemplate.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.feelsokman.androidtemplate.BuildConfig
import com.feelsokman.androidtemplate.core.coroutine.DefaultDispatcherProvider
import com.feelsokman.androidtemplate.core.coroutine.DispatcherProvider
import com.feelsokman.androidtemplate.preferences.AppPreferences
import com.feelsokman.androidtemplate.preferences.LocalAppPreferences
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        const val BASE_URL = "key.base.url"
    }

    @Provides
    @Singleton
    fun providesApplicationContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesApplicationResources(context: Context): Resources {
        return context.resources
    }

    @Provides
    @Singleton
    internal fun providesCache(applicationContext: Context): Cache {
        return Cache(applicationContext.cacheDir, 10 * 1024 * 1024)
    }

    @Provides
    @Singleton
    fun providesBus(): Bus = Bus()

    @Provides
    fun providesFirebaseAnalytics(applicationContext: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(applicationContext)
    }

    @Provides
    @Named(BASE_URL)
    fun providesBaseUrl(): String {
        return BuildConfig.serverUrl
    }

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().setPrettyPrinting().create()
    }

    @Provides
    internal fun providesExecutionScheduler() = Schedulers.io()

    @Singleton
    @Provides
    internal fun providesDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }

    @Provides
    @Singleton
    internal fun providesPreferences(context: Context): AppPreferences = LocalAppPreferences(context)
}
