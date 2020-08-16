package com.feelsokman.androidtemplate.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.feelsokman.androidtemplate.core.coroutine.DispatcherProvider
import com.feelsokman.androidtemplate.core.features.FlagProvider
import com.feelsokman.androidtemplate.net.connectivity.ConnectivityChecker
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import com.feelsokman.androidtemplate.net.net.JsonPlaceHolderService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    internal fun providesNetworkResolver(applicationContext: Context): ConnectivityChecker {
        return object : ConnectivityChecker {
            override fun isConnected(): Boolean {
                val connectivityManager: ConnectivityManager? =
                    applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
                return activeNetworkInfo != null && activeNetworkInfo.isConnected
            }
        }
    }

    @Provides
    @Singleton
    internal fun providesHttpLoggingInterceptor(
        flagProvider: FlagProvider
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when {
                flagProvider.isDebugEnabled -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    internal fun providesOkHttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        flagProvider: FlagProvider
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .apply {
                addInterceptor(httpLoggingInterceptor)
                cache(cache)
                if (flagProvider.isDebugEnabled) {
                    addNetworkInterceptor(StethoInterceptor())
                }
            }.build()
    }

    @Provides
    internal fun providesRetrofit(
        @Named(AppModule.BASE_URL) baseUrl: String,
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
        dispatcherProvider: DispatcherProvider
    ): JsonPlaceHolderClient = JsonPlaceHolderClient(jsonPlaceHolderService, dispatcherProvider)
}
