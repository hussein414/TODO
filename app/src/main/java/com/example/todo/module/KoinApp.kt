package com.example.todo.module

import android.app.Application
import androidx.room.Room
import com.example.todo.data.db.TodoDatabase
import com.example.todo.data.repository.TodoRepo
import com.example.todo.data.repository.TodoRepositoryImpl
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module {
                single {
                    Room.databaseBuilder(this@KoinApp, TodoDatabase::class.java, "todo_db").build()
                }
                single {
                    TodoRepositoryImpl(database = get())
                } bind TodoRepo::class
            })
        }
    }
}