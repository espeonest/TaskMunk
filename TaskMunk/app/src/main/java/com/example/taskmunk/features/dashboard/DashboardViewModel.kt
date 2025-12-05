package com.example.taskmunk.features.dashboard

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.taskmunk.data.Task
import com.example.taskmunk.data.TaskDatabaseHelper

class DashboardViewModel  (application: Application) : AndroidViewModel(application) {
    private val dbHelper = TaskDatabaseHelper(application)


    var tasks = mutableStateOf<List<Task>>(emptyList())
        private set

    //Load existing Tasks
    init {
        loadTasks()
//TODO: Leaving here for now to test card/layout of dashboard

//        //Fake tasks, delete later ****
//        tasks.value = listOf(
//            Task(
//                title = "Fake Task 1",
//                description = "UI Testing",
//                dueDate = "Dec 07,2025",
//                priority = "High",
//                category = "Work",
//                status = "In Progress"
//            )
//        )

}
    //Load all Tasks using dbHelper
    fun loadTasks(){
        tasks.value = dbHelper.getAllTasks()
    }

    //Sort by Status (To Do, In Progress, Completed)
    fun sortByStatus(){

    }

}