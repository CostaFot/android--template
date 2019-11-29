package com.feelsokman.androidtemplate.net.domain

import com.feelsokman.androidtemplate.net.domain.model.DomainTodo
import com.feelsokman.androidtemplate.net.net.model.ApiTodo

class TodoMapper {

    fun map(apiTodo: ApiTodo?): DomainTodo {
        return DomainTodo(
            id = apiTodo!!.id!!,
            title = apiTodo.title!!,
            completed = apiTodo.completed!!
        )
    }
}
