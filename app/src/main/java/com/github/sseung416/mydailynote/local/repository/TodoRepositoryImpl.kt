package com.github.sseung416.mydailynote.local.repository

import com.github.sseung416.mydailynote.local.dao.TodoDao
import com.github.sseung416.mydailynote.local.dto.Todo

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override suspend fun insertTodo(todo: Todo) =
        dao.insert(todo)

    override suspend fun updateTodo(todo: Todo) =
        dao.update(todo)

    override suspend fun deleteTodo(todo: Todo) =
        dao.delete(todo)
}