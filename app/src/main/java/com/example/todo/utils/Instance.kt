package com.example.todo.utils

import android.annotation.SuppressLint
import com.example.todo.data.model.TodoEntity
import java.text.SimpleDateFormat
import java.util.Date

object Instance {
    val TodoEntity.addDate:String @SuppressLint("SimpleDateFormat")
    get() = SimpleDateFormat("yyyy/MM/dd hh:mm").format(Date(adedd))
}