package com.example.taskmunk.data

import com.example.taskmunk.utils.getDateString

data class Task(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val dueDate: String = getDateString(),
    val priority: String = "Low",
    val category: String = "Personal",
    val status: String = "To Do",
    val reminder: String = "None",
    val dateCreated: String = getDateString(),
    val dateCompleted: String? = null
)