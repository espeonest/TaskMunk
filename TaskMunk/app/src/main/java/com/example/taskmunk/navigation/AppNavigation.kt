package com.example.taskmunk.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmunk.R
import com.example.taskmunk.features.calendar.CalendarScreen
import com.example.taskmunk.features.dashboard.DashboardScreen
import com.example.taskmunk.features.dashboard.DashboardViewModel
import com.example.taskmunk.features.home.HomeScreen
import com.example.taskmunk.features.splash.SplashScreen
import com.example.taskmunk.features.tasks.AddTaskScreen
import com.example.taskmunk.features.tasks.EditTaskScreen
import com.example.taskmunk.features.tasks.TaskDetailsScreen
import com.example.taskmunk.features.tasks.TaskViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavigation(
    navController: NavController,
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel,
    taskViewModel: TaskViewModel,
    snackbarHostState: SnackbarHostState
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarMessage = stringResource(R.string.snackbar_delete_message)
    val snackbarActionLabel = stringResource(R.string.snackbar_undo_button)

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
        }

        //Dashboard Screen
        composable("dashboard_screen") {
            dashboardViewModel.loadTasks()
            DashboardScreen(
                navController = navController,
                dashboardViewModel = dashboardViewModel,
                onTaskSelected = { task ->
                    taskViewModel.selectTask(task)
                    navController.navigate("task_details")
                }
            )
        }

        //Task Details Screen
        composable("task_details") {
            TaskDetailsScreen(
                viewModel = taskViewModel,
                onEditSelected = { navController.navigate("edit_task") },
                onBackClick = { navController.popBackStack() },
                onTaskDeleted = { deletedTask ->
                    navController.navigate("dashboard_screen")

                    coroutineScope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = snackbarMessage,
                            duration = SnackbarDuration.Short,
                            actionLabel = snackbarActionLabel
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            taskViewModel.restoreTask(
                                deletedTask = deletedTask,
                                onComplete = { dashboardViewModel.loadTasks() }
                            )
                        }
                    }
                }
            )
        }

        composable("edit_task") {
            EditTaskScreen(
                viewModel = taskViewModel,
                onTaskSaved = {
                    navController.navigate("dashboard_screen") {
                        popUpTo("edit_task") { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("add_task") {
            taskViewModel.selectTask()
            AddTaskScreen(
                viewModel = taskViewModel,
                onTaskSaved = {
                    navController.navigate("dashboard_screen") {
                        popUpTo("add_task") { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("calendar") {
            CalendarScreen(navController = navController)
        }
    }
}
