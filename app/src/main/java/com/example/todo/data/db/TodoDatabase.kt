package com.example.todo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.model.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}