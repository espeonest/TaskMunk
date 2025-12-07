package com.example.taskmunk.features.dashboard

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.taskmunk.data.Task
import com.example.taskmunk.data.TaskDatabaseHelper

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = TaskDatabaseHelper(application)

    var tasks = mutableStateOf<List<Task>>(emptyList())
        private set

    //List of all status options
    val statusOptions = listOf("To Do", "In Progress", "Completed")

    //Selected status
    var selectedStatus by mutableStateOf(setOf<String>())
        private set

    //Search Bar text
    var searchBarText by mutableStateOf("")
        private set

    //Filter tasks in the lazy col by status using filter chips
    //OR filter tasks by title name using the search bar
    //If empty then all tasks will be visible
    val filterTasks: List<Task>
        get() = tasks.value.filter { task ->
            (selectedStatus.isEmpty() || selectedStatus.contains(task.status)) &&
                    (searchBarText.isBlank() || task.title.contains(
                        searchBarText,
                        ignoreCase = true
                    ))
        }

    //Load existing Tasks
    init {
        loadTasks()
    }

    //Load all Tasks using dbHelper
    fun loadTasks() {
        tasks.value = dbHelper.getAllTasks()
    }

    //Status chip states
    fun toggleStatusOptions(status: String) {
        selectedStatus = if (selectedStatus.contains(status)) {
            //Remove selected status
            selectedStatus - status
        } else {
            //Add selected status
            selectedStatus + status
        }

    }

    //Search bar text, updated once a user types their search
    fun onSearchBarTextChanged(updatedText: String) {
        searchBarText = updatedText
    }
}