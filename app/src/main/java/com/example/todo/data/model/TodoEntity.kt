package com.example.todo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("name")
    val title: String,
    @ColumnInfo("sub_title")
    val subTitle: String,
    @ColumnInfo("done")
    val done: Boolean = false,
    @ColumnInfo("added")
    val adedd: Long = System.currentTimeMillis()

)
