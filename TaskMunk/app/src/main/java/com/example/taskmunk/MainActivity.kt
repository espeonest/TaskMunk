package com.example.taskmunk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.taskmunk.features.dashboard.DashboardViewModel
import com.example.taskmunk.features.home.HomeScreen
import com.example.taskmunk.features.signin.SignUpScreen
import com.example.taskmunk.features.tasks.TaskViewModel
import com.example.taskmunk.navigation.AppNavigation
import com.example.taskmunk.ui.theme.TaskMunkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskMunkTheme {
                val navController = rememberNavController()

                val dashboardViewModel: DashboardViewModel = viewModel ()
                val taskViewModel: TaskViewModel = viewModel ()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(navController = navController, dashboardViewModel = dashboardViewModel, taskViewModel = taskViewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}