package com.example.taskmunk.features.dashboard

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.taskmunk.R
import com.example.taskmunk.data.Task
import com.example.taskmunk.data.TaskDatabaseHelper

class DashboardViewModel  (application: Application) : AndroidViewModel(application) {
    private val dbHelper = TaskDatabaseHelper(application)


    var tasks = mutableStateOf<List<Task>>(emptyList())
        private set

    //List of all status options
    val statusOptions = listOf("To Do", "In Progress", "Completed")

    //Selected status
    var selectedStatus by mutableStateOf(setOf<String>())
        private set

    val filterTasks: List<Task>
        get() = if (selectedStatus.isEmpty()) {
            tasks.value
        } else {
        tasks.value.filter { it.status in selectedStatus }
        }

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
//                status = "To Do"
//            ),
//            Task(
//                title = "Fake Task 1",
//                description = "UI Testing",
//                dueDate = "Dec 17,2025",
//                priority = "Medium",
//                category = "Assignment",
//                status = "Completed"
//            ),
//            Task(
//                title = "Fake Task 1",
//                description = "UI Testing",
//                dueDate = "Dec 07,2025",
//                priority = "Low",
//                category = "Personal",
//                status = "In Progress"
//            ), Task(
//                title = "Fake Task 1",
//                description = "UI Testing",
//                dueDate = "Dec 07,2025",
//                priority = "High",
//                category = "Work",
//                status = "Completed"
//            ),
//            Task(
//                title = "Fake Task 1",
//                description = "UI Testing",
//                dueDate = "Dec 07,2025",
//                priority = "High",
//                category = "Assignment",
//                status = "In Progress"
//            ),
//            Task(
//                title = "Fake Task 1",
//                description = "UI Testing",
//                dueDate = "Dec 07,2025",
//                priority = "Medium",
//                category = "Work",
//                status = "To Do"
//            ),
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

    //Status chip states
    fun toggleStatusOptions(status: String){
        selectedStatus = if (selectedStatus.contains(status)) {
           //Remove selected status
            selectedStatus - status
        } else {
            //Add selected status
            selectedStatus + status
        }

    }
}