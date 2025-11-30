package com.example.taskmunk.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavigation(navController: NavController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = "splash_screen", // once we have one
        modifier = modifier
    ){
        // TODO: everything
    }
}