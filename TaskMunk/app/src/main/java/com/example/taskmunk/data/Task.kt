package com.example.taskmunk.data

import com.example.taskmunk.utils.GetFormattedDate
import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var description: String = "",
    var dueDate: String = "",
    var priority: String = "Low",
    var category: String = "Personal",
    var status: String = "To Do",
    var reminder: String = "None",
    var dateCreated: String = GetFormattedDate(),
    var dateCompleted: String? = null
)