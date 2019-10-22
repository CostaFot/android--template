package com.feelsokman.net.domain

import com.feelsokman.net.domain.model.DomainTodo
import com.feelsokman.net.net.JsonPlaceHolderService
import com.feelsokman.net.net.model.ApiTodo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class JsonPlaceHolderClient(
    private val jsonPlaceHolderService: JsonPlaceHolderService,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getTodos(): DomainTodo? {
        return withContext(dispatcher) {
            try {
                val response = jsonPlaceHolderService.getTodo(1)
                if (response.isSuccessful) {
                    val apiTodo: ApiTodo? = response.body()
                    if (apiTodo != null) {
                        return@withContext DomainTodo(
                            apiTodo.id!!,
                            apiTodo.title!!,
                            apiTodo.completed!!
                        )
                    } else {
                        return@withContext null
                    }
                } else {
                    return@withContext null
                }
            } catch (exception: Exception) {
                return@withContext null
            }
        }
    }
}