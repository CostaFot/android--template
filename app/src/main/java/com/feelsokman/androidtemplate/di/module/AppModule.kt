package com.feelsokman.androidtemplate.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import com.feelsokman.androidtemplate.BuildConfig
import com.feelsokman.net.domain.JsonPlaceHolderClient
import com.feelsokman.net.net.JsonPlaceHolderService
import com.feelsokman.net.net.resolver.NetworkResolver
import com.feelsokman.storage.LocalStorage
import com.feelsokman.storage.Storage
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        const val BASE_URL = "key.base.url"
    }

    @Provides
    @Singleton
    internal fun providesCache(context: Context): Cache {
        return Cache(context.cacheDir, 10 * 1024 * 1024)
    }

    @Provides
    @Singleton
    fun providesContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesApplicationResources(context: Context): Resources {
        return context.resources
    }

    @Provides
    @Singleton
    fun providesBus(): Bus = Bus()

    @Provides
    fun providesFirebaseAnalytics(context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    @Provides
    fun providesDebugFlag(): Boolean {
        return BuildConfig.DEBUG
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

    @Provides
    internal fun providesDefaultDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    internal fun providesLocalStorage(context: Context): Storage = LocalStorage(context)

    @Provides
    internal fun providesNetworkResolver(context: Context): NetworkResolver {
        return object : NetworkResolver {
            override fun isConnected(): Boolean {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
                return activeNetworkInfo != null && activeNetworkInfo.isConnected
            }
        }
    }

    @Provides
    @Singleton
    internal fun providesHttpLoggingInterceptor(
        isDebugEnabled: Boolean
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when {
                isDebugEnabled -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    internal fun providesOkHttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .apply {
                addInterceptor(httpLoggingInterceptor)
                cache(cache)
            }.build()
    }

    @Provides
    internal fun providesRetrofit(
        @Named(BASE_URL) baseUrl: String,
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .apply {
                baseUrl(baseUrl)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create(gson))
            }.build()
    }

    @Provides
    internal fun providesJsonPlaceHolderService(
        retrofit: Retrofit
    ): JsonPlaceHolderService = retrofit.create(JsonPlaceHolderService::class.java)

    @Provides
    internal fun providesJsonPlaceHolderSClient(
        jsonPlaceHolderService: JsonPlaceHolderService,
        dispatcher: CoroutineDispatcher
    ): JsonPlaceHolderClient = JsonPlaceHolderClient(jsonPlaceHolderService, dispatcher)
}