package com.feelsokman.net.domain.usecases

import com.feelsokman.net.domain.error.DataSourceError

abstract class BaseDisposableUseCase {

    interface Callback<T> {

        fun onLoadingStarted()

        fun onSuccess(result: T)

        fun onError(error: DataSourceError)
    }

    abstract fun cancelAll()
}