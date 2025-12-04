package com.example.taskmunk.features.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskmunk.R
import com.example.taskmunk.data.Task

class TaskViewModel: ViewModel() {
    var selectedTask by mutableStateOf(Task())
        private set
    val priorityOptions = listOf(R.string.priority_low,
        R.string.priority_medium, R.string.priority_high)
    val categoryOptions = listOf(R.string.category_work,
        R.string.category_personal, R.string.category_assignment)
    val statusOptions = listOf(R.string.category_todo,
        R.string.category_inprogress, R.string.category_completed)
    val reminderOptions = listOf(R.string.reminder_none,
        R.string.reminder_hourbefore, R.string.reminder_daybefore)

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
