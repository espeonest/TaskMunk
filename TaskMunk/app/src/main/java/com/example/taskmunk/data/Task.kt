package com.example.taskmunk.data

import java.time.LocalDate

data class Task(
    val title: String = "",
    val description: String = "",
    val dueDate: String = "",
    val priority: String = "Low",
    val category: String = "Personal",
    val status: String = "To Do",
    val reminder: String = "None",
    val dateCreated: String = LocalDate.now().toString(),
    val dateCompleted: String? = null
)