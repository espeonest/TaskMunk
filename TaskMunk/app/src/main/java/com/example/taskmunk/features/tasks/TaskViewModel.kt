package com.example.taskmunk.features.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskmunk.R
import com.example.taskmunk.data.Task
import com.example.taskmunk.validation.ValidationResult

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
        val validationResult = validateFields()
        if (validationResult is ValidationResult.Error) {
            // TODO: Display the error somehow
        }
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

    // Validate fields and return the first error if any
    private fun validateFields(): ValidationResult {
        val validations = listOf(
            validateTitle(selectedTask.title),
            validateDescription(selectedTask.description),
            validateDueDate(selectedTask.dueDate),
            validatePriority(selectedTask.priority),
            validateCategory(selectedTask.category),
            validateStatus(selectedTask.status),
            validateReminder(selectedTask.reminder),
            validateDateCreated(selectedTask.dateCreated),
            validateDateCompleted(selectedTask.dateCompleted)
        )
        val firstError = validations.firstOrNull { it is ValidationResult.Error }
        if (firstError != null) {
            return firstError
        }
        return ValidationResult.Success
    }

    private fun validateTitle(title: String): ValidationResult {
        if (title.isBlank()) {
            return ValidationResult.Error("Title cannot be empty")
        }
        return ValidationResult.Success
    }

    private fun validateDescription(amount: String): ValidationResult {
        // No validation needed
        return ValidationResult.Success
    }

    private fun validateDueDate(dueDate: String): ValidationResult {
        if (dueDate.isBlank()) {
            return ValidationResult.Error("Due date cannot be empty")
        }
        return ValidationResult.Success
    }

    private fun validatePriority(priority: String): ValidationResult {
        if (priority.isBlank()) {
            return ValidationResult.Error("Priority cannot be empty (how did you even do that?)")
        }
        return ValidationResult.Success
    }

    private fun validateCategory(category: String): ValidationResult {
        if (category.isBlank()) {
            return ValidationResult.Error("Category cannot be empty (how did you even do that?)")
        }
        return ValidationResult.Success
    }

    private fun validateStatus(status: String): ValidationResult {
        if (status.isBlank()) {
            return ValidationResult.Error("Status cannot be empty (how did you even do that?)")
        }
        return ValidationResult.Success
    }

    private fun validateReminder(reminder: String): ValidationResult {
        if (reminder.isBlank()) {
            return ValidationResult.Error("Reminder cannot be empty (how did you even do that?)")
        }
        return ValidationResult.Success
    }

    private fun validateDateCreated(dateCreated: String): ValidationResult {
        if (dateCreated.isBlank()) {
            return ValidationResult.Error("Date created cannot be empty")
        }
        return ValidationResult.Success
    }

    private fun validateDateCompleted(dateCompleted: String?): ValidationResult {
        if (dateCompleted != null) {
            if (dateCompleted.isBlank()) {
                return ValidationResult.Error("Date completed cannot be empty")
            }
            // TODO: Validate date completed > date created
        }
        return ValidationResult.Success
    }
}
