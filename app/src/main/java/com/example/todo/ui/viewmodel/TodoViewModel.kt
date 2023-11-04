package com.example.todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.model.TodoEntity
import com.example.todo.data.repository.TodoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodoViewModel : ViewModel(), KoinComponent {
    private val repository: TodoRepo by inject()
    private val _todos: MutableStateFlow<List<TodoEntity>> = MutableStateFlow(emptyList())
    val todo = _todos.asStateFlow()

    fun getTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTodos().collect { data ->
                _todos.update { data }
            }
        }
    }

    fun updateTodo(todoEntity: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todoEntity)
        }
    }

    fun addTodo(todoEntity: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todoEntity)
        }
    }

    fun deleteTodo(todoEntity: TodoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todoEntity)
        }
    }


    init {
        getTodos()
    }
}