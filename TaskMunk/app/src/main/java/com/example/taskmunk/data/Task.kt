package com.example.taskmunk.data

import com.example.taskmunk.utils.getDateString

data class Task(
    val id: Int = 0,
    var title: String = "",
    var description: String = "",
    var dueDate: String = "",
    var priority: String = "Low",
    var category: String = "Personal",
    var status: String = "To Do",
    var reminder: String = "None",
    var dateCreated: String = getDateString(),
    var dateCompleted: String? = null
)