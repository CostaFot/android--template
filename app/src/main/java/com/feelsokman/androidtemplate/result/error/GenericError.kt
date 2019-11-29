package com.feelsokman.androidtemplate.result.error

sealed class GenericError {
    data class HttpError(val code: Int) : GenericError()
    data class UnsupportedEndpointError(val message: String) : GenericError()
    data class UnknownError(val message: String) : GenericError()
    object ConnectionError : GenericError()
}
