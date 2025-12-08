package com.example.taskmunk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.taskmunk.components.BottomNavigationBar
import com.example.taskmunk.components.FabButton
import com.example.taskmunk.components.TopNavigationBar
import com.example.taskmunk.features.dashboard.DashboardViewModel
import com.example.taskmunk.features.settings.SettingsViewModel
import com.example.taskmunk.features.tasks.TaskViewModel
import com.example.taskmunk.navigation.AppNavigation
import com.example.taskmunk.ui.theme.TaskMunkTheme
import kotlin.collections.contains

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = viewModel()
            val isDark = settingsViewModel.isDarkMode.value

            TaskMunkTheme(darkTheme = isDark) {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()

                val dashboardViewModel: DashboardViewModel = viewModel()
                val taskViewModel: TaskViewModel = viewModel()

                // Screens where top bar and bottom bar should be visible
                val bottomBarScreens = listOf("dashboard_screen","settings_screen")
                val topBarScreens = listOf("dashboard_screen")

                // Get the current route
                val currentRoute = backStackEntry.value?.destination?.route

                // Scaffold to handle layout with top bar, bottom bar, and FAB
                Scaffold(
//                    topBar = {
//                        if (currentRoute in topBarScreens) {
//                            TopNavigationBar(navController)
//                        }
//                    },
                    bottomBar = {
                        if (currentRoute in bottomBarScreens) {
                            BottomNavigationBar(navController)
                        }
                    },
                    floatingActionButton = {
                        if (currentRoute in bottomBarScreens) {
                            FabButton(
                                onAddClick = { navController.navigate("add_task") }
                            )
                        }
                    }
                ) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        dashboardViewModel = dashboardViewModel,
                        taskViewModel = taskViewModel,
                        settingsViewModel = settingsViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}