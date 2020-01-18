package com.feelsokman.androidtemplate.net.domain

import com.feelsokman.androidtemplate.coroutine.DispatcherProvider
import com.feelsokman.androidtemplate.net.domain.model.DomainTodo
import com.feelsokman.androidtemplate.net.net.JsonPlaceHolderService
import com.feelsokman.androidtemplate.result.Result
import com.feelsokman.androidtemplate.result.attempt
import com.feelsokman.androidtemplate.result.error.ErrorMapper
import com.feelsokman.androidtemplate.result.error.GenericError
import kotlinx.coroutines.withContext

class JsonPlaceHolderClient(
    private val jsonPlaceHolderService: JsonPlaceHolderService,
    private val dispatcherProvider: DispatcherProvider
) {
    suspend fun getTodo(): Result<GenericError, DomainTodo> {
        return withContext(dispatcherProvider.io) {
            attempt(ErrorMapper::map) {
                val apiTodo = jsonPlaceHolderService.getTodo(1)
                DomainTodo(apiTodo.id!!, apiTodo.title!!, apiTodo.completed!!)
            }
        }
    }
}
