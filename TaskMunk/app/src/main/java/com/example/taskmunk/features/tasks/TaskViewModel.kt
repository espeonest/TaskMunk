package com.example.taskmunk.features.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskmunk.data.Task

class TaskViewModel: ViewModel() {
    var selectedTask by mutableStateOf(Task())
        private set
    val priorityOptions = listOf("Low", "Medium", "High")
    val categoryOptions = listOf("Work", "Personal", "Assignment")
    val statusOptions = listOf("To Do", "In Progress", "Completed")
    val reminderOptions = listOf("None", "1 hour before", "1 day before")

    fun selectTask(task: Task, onComplete: () -> Unit = {}) {
        selectedTask = task
        onComplete()
    }

    fun saveTask(onComplete: () -> Unit = {}) {
        // TODO: Save task to database
        selectedTask = Task()
        onComplete()
    }

    fun deleteTask(onComplete: () -> Unit = {}) {
        // TODO: Delete task from database
        selectedTask = Task()
        onComplete()
    }
    
    fun onTitleChange(title: String) {
        selectedTask.title = title
    }

    fun onDescriptionChange(description: String) {
        selectedTask.description = description
    }

    fun onDueDateChange(dueDate: String) {
        selectedTask.dueDate = dueDate
    }
    
    fun onPriorityChange(priority: String) {
        selectedTask.priority = priority
    }
    
    fun onCategoryChange(category: String) {
        selectedTask.category = category
    }

    fun onStatusChange(status: String) {
        selectedTask.status = status
    }

    fun onReminderChange(reminder: String) {
        selectedTask.reminder = reminder
    }
}
