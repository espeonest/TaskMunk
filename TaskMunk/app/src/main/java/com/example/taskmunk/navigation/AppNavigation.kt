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
import com.example.taskmunk.features.home.HomeScreen
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
                navController.navigate("home_screen"){
                    popUpTo("splash_screen") { inclusive = true }
                }
            })
        }

        //Login Screen
        composable("home_screen"){
            HomeScreen(modifier = Modifier, onLoginSuccess = {
                navController.navigate("dashboard_screen")
            })
            // I assume there will need to be a viewmodel with user data
            // passed to the dashboard but will discuss later
        }

        //Dashboard Screen
        composable("dashboard_screen"){
            DashboardScreen(
                navController = navController,
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
