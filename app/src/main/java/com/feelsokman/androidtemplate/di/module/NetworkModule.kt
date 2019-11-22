package com.feelsokman.androidtemplate.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import com.feelsokman.androidtemplate.net.net.JsonPlaceHolderService
import com.feelsokman.androidtemplate.net.net.resolver.NetworkResolver
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

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
    ): JsonPlaceHolderService = retrofit.create(
        JsonPlaceHolderService::class.java
    )

    @Provides
    internal fun providesJsonPlaceHolderSClient(
        jsonPlaceHolderService: JsonPlaceHolderService,
        dispatcher: CoroutineDispatcher
    ): JsonPlaceHolderClient =
        JsonPlaceHolderClient(
            jsonPlaceHolderService,
            dispatcher
        )
}
