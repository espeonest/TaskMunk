package com.example.taskmunk.features.tasks

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.taskmunk.R
import com.example.taskmunk.data.Task
import com.example.taskmunk.data.TaskDatabaseHelper
import com.example.taskmunk.utils.getDateString
import com.example.taskmunk.validation.ValidationResult

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    val dbHelper = TaskDatabaseHelper(application)
    val priorityOptions = listOf(
        R.string.priority_low, R.string.priority_medium, R.string.priority_high
    )
    val categoryOptions = listOf(
        R.string.category_work, R.string.category_personal, R.string.category_assignment
    )
    val statusOptions = listOf(
        R.string.status_todo, R.string.status_inprogress, R.string.status_completed
    )
    val reminderOptions = listOf(
        R.string.reminder_none, R.string.reminder_hourbefore, R.string.reminder_daybefore
    )

    var selectedTask by mutableStateOf(Task())
        private set
    var titleInput by mutableStateOf(selectedTask.title)
    var descriptionInput by mutableStateOf(selectedTask.description)
    var dueDateInput by mutableStateOf(selectedTask.dueDate)
    var priorityInput by mutableStateOf(selectedTask.priority)
    var categoryInput by mutableStateOf(selectedTask.category)
    var statusInput by mutableStateOf(selectedTask.status)
    var reminderInput by mutableStateOf(selectedTask.reminder)
    var dateCompletedInput by mutableStateOf(selectedTask.dateCompleted)

    fun selectTask(task: Task = Task(), onComplete: () -> Unit = {}) {
        selectedTask = task
        titleInput = task.title
        descriptionInput = task.description
        dueDateInput = task.dueDate
        priorityInput = task.priority
        categoryInput = task.category
        statusInput = task.status
        reminderInput = task.reminder
        dateCompletedInput = task.dateCompleted
        onComplete()
    }

    fun saveTask(onComplete: () -> Unit = {}, context: Context) {
        // Validate fields before saving
        val validationResult = validateFields()
        if (validationResult is ValidationResult.Error) {
            Toast.makeText(context, validationResult.message, Toast.LENGTH_SHORT).show()
            return
        }

        // Set date completed
        if (statusInput != context.getString(R.string.status_completed)) dateCompletedInput = null
        else if (selectedTask.dateCompleted == null) dateCompletedInput = getDateString()

        // Save task if it's a new task, otherwise update it
        if (selectedTask.id == 0) dbHelper.insertTask(
            titleInput,
            descriptionInput,
            dueDateInput,
            priorityInput,
            categoryInput,
            statusInput,
            reminderInput,
            getDateString(),
            dateCompletedInput
        )
        else dbHelper.updateTask(
            selectedTask.id,
            titleInput,
            descriptionInput,
            dueDateInput,
            priorityInput,
            categoryInput,
            statusInput,
            reminderInput,
            selectedTask.dateCreated,
            dateCompletedInput
        )
        selectedTask = Task()
        onComplete()
    }

    fun deleteTask(onComplete: (Task) -> Unit = {}) {
        val deletedTask = selectedTask
        dbHelper.deleteTask(selectedTask.id)
        selectedTask = Task()
        onComplete(deletedTask)
    }

    fun restoreTask(deletedTask: Task, onComplete: () -> Unit = {}) {
        dbHelper.insertTask(deletedTask)
        onComplete()
    }

    fun onTitleChange(title: String) {
        titleInput = title
    }

    fun onDescriptionChange(description: String) {
        descriptionInput = description
    }

    fun onDueDateChange(dueDate: String) {
        dueDateInput = dueDate
    }

    fun onPriorityChange(priority: String) {
        priorityInput = priority
    }

    fun onCategoryChange(category: String) {
        categoryInput = category
    }

    fun onStatusChange(status: String) {
        statusInput = status
    }

    fun onReminderChange(reminder: String) {
        reminderInput = reminder
    }

    // Validate fields and return the first error if any
    private fun validateFields(): ValidationResult {
        val validations = listOf(
            validateTitle(titleInput),
            validateDescription(descriptionInput),
            validateDueDate(dueDateInput),
            validatePriority(priorityInput),
            validateCategory(categoryInput),
            validateStatus(statusInput),
            validateReminder(reminderInput),
            validateDateCompleted(dateCompletedInput)
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

    private fun validateDateCompleted(dateCompleted: String?): ValidationResult {
        if (dateCompleted != null) {
            if (dateCompleted.isBlank()) {
                return ValidationResult.Error("Date completed cannot be empty")
            }
        }
        return ValidationResult.Success
    }
}
