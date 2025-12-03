package com.example.taskmunk.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmunk.features.signin.SignUpScreen
import com.example.taskmunk.features.signin.SignupViewModel
import com.example.taskmunk.features.splash.SplashScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavigation(navController: NavController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = "splash_screen",
        modifier = modifier
    ){
        composable("splash_screen"){
            SplashScreen(onTimeout = {
                navController.navigate("login_screen"){
                    popUpTo("splash_screen") { inclusive = true }
                }
            })
        }

        // TODO: everything
    }
}