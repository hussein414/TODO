package com.example.todo.data.repository

import com.example.todo.data.model.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepo {

    suspend fun addTodo(todo: TodoEntity)

    suspend fun deleteTodo(todo: TodoEntity)

    suspend fun updateTodo(todo: TodoEntity)


    fun getTodos(): Flow<List<TodoEntity>>

}