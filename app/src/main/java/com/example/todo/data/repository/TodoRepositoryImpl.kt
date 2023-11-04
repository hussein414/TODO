package com.example.todo.data.repository

import com.example.todo.data.db.TodoDatabase
import com.example.todo.data.model.TodoEntity
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val database: TodoDatabase) : TodoRepo {
    private val dao = database.todoDao
    override suspend fun addTodo(todo: TodoEntity) =dao.addTodo(todo)

    override suspend fun deleteTodo(todo: TodoEntity) =dao.deleteTodo(todo)

    override suspend fun updateTodo(todo: TodoEntity) =dao.updateTodo(todo)

    override fun getTodos(): Flow<List<TodoEntity>> = dao.getTodos()
}