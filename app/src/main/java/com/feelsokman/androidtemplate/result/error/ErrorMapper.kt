package com.feelsokman.androidtemplate.result.error

import retrofit2.HttpException
import java.io.IOException

// add more exceptions are you see fit
object ErrorMapper {

    fun map(error: Throwable): GenericError {
        return when (error) {
            is HttpException -> GenericError.HttpError(error.code())
            is UnsupportedOperationException -> GenericError.UnsupportedEndpointError(error.message ?: "")
            is IOException -> GenericError.ConnectionError
            else -> GenericError.UnknownError(error.message ?: "")
        }
    }
}
