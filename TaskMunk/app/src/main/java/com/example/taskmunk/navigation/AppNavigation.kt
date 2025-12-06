package com.example.taskmunk.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmunk.features.dashboard.DashboardScreen
import com.example.taskmunk.features.dashboard.DashboardViewModel
import com.example.taskmunk.features.home.HomeScreen
import com.example.taskmunk.features.splash.SplashScreen
import com.example.taskmunk.features.tasks.AddTaskScreen
import com.example.taskmunk.features.tasks.EditTaskScreen
import com.example.taskmunk.features.tasks.TaskDetailsScreen
import com.example.taskmunk.features.tasks.TaskViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel,
    taskViewModel: TaskViewModel
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = "splash_screen",
        modifier = modifier
    ) {
        //Splash Screen
        composable("splash_screen") {
            SplashScreen(onTimeout = {
                navController.navigate("home_screen") {
                    popUpTo("splash_screen") { inclusive = true }
                }
            })
        }

        //Login Screen
        composable("home_screen") {
            HomeScreen(modifier = Modifier, onLoginSuccess = {
                navController.navigate("dashboard_screen") {
                    popUpTo("home_screen") { inclusive = true }
                }
            })
            // I assume there will need to be a viewmodel with user data
            // passed to the dashboard but will discuss later
        }

        //Dashboard Screen
        composable("dashboard_screen") {
            DashboardScreen(
                navController = navController,
               // tasks = dashboardViewModel.tasks.value,
            )
        }

        //Task Details Screen
        composable("task_details") {
            TaskDetailsScreen(
                viewModel = taskViewModel,
                onEditSelected = { navController.navigate("edit_task") },
                onTaskDeleted = { navController.navigate("dashboard_screen") }
            )
        }

        composable("edit_task") {
            EditTaskScreen(
                viewModel = taskViewModel,
                onTaskSaved = {
                    navController.navigate("dashboard_screen") {
                        popUpTo("edit_task") { inclusive = true }
                    }
                }
            )
        }

        composable("add_task") {
            AddTaskScreen(
                viewModel = taskViewModel,
                onTaskSaved = {
                    navController.navigate("dashboard_screen") {
                        popUpTo("add_task") { inclusive = true }
                    }
                }
            )
        }
    }

    // TODO: everything
}
