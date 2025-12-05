package com.example.taskmunk.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmunk.features.dashboard.DashboardScreen
import com.example.taskmunk.features.dashboard.DashboardViewModel
import com.example.taskmunk.features.signin.SignUpScreen
import com.example.taskmunk.features.signin.SignupViewModel
import com.example.taskmunk.features.splash.SplashScreen
import com.example.taskmunk.features.tasks.TaskDetailsScreen
import com.example.taskmunk.features.tasks.TaskViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavigation(navController: NavController, modifier: Modifier = Modifier, dashboardViewModel: DashboardViewModel, taskViewModel: TaskViewModel) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = "splash_screen",
        modifier = modifier
    ){
        //Splash Screen
        composable("splash_screen"){
            SplashScreen(onTimeout = {
                //Need to change this to login screen, just did this for testing
                navController.navigate("dashboard_screen"){
                    popUpTo("splash_screen") { inclusive = true }
                }
            })
        }

        //Dashboard Screen
        composable("dashboard_screen"){
            DashboardScreen(
                navController = navController,
                dashboardViewModel = dashboardViewModel,
                taskViewModel = taskViewModel,
                tasks = dashboardViewModel.tasks.value,
            )
        }

        //Task Details Screen
        // Not sure if navEdit / navDelete should be empty?  but put for now to see if card directs to there
        composable("task_details") {
            TaskDetailsScreen(
                viewModel = taskViewModel,
                navEdit = {},
                navDelete = {}
            ) 
        }
    }

        // TODO: everything
    }
