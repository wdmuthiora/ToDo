package com.example.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    val description: String,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null,
    val timeToComplete: Int? = null
)
