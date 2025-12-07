package com.example.taskmunk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskmunk.features.dashboard.DashboardScreen
import com.example.taskmunk.features.dashboard.DashboardScreenTopBar
import com.example.taskmunk.features.home.HomeScreen
import com.example.taskmunk.features.tasks.AddTaskScreen
import com.example.taskmunk.features.tasks.TaskViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopNavigationBar(navController: NavController){
    val viewModel: TopNavigationBarViewModel = viewModel()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedScreen = viewModel.selectedScreen
    val taskViewModel: TaskViewModel = viewModel()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerItem("Dashboard", selectedScreen, scope, drawerState) {
                    viewModel.onScreenSelected("Dashboard")
                }
                DrawerItem("Profile", selectedScreen, scope, drawerState) {
                    viewModel.onScreenSelected("Profile")
                }
                DrawerItem("Add Task", selectedScreen, scope, drawerState) {
                    viewModel.onScreenSelected("Add Task")
                }
                DrawerItem("Settings", selectedScreen, scope, drawerState) {
                    viewModel.onScreenSelected("Settings")
                }
                DrawerItem("Log out", selectedScreen, scope, drawerState) {
                    navController.navigate("signup_screen") {
                        popUpTo("dashboard_screen") { inclusive = true }
                    }
                }
            }
        }
    ) {
        Row (modifier= Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically, // aligns all children vertically
            horizontalArrangement = Arrangement.Start // keep everything aligned start
        ){
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .clickable {
                        scope.launch { drawerState.open() }
                    }
            )


        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp), // space for top bar
            contentAlignment = Alignment.Center
        )

        {

            when (selectedScreen) {
//                "Dashboard" -> DashboardScreen(
//                    navController = navController,
//                    // tasks = dashboardViewModel.tasks.value,
//                )
                "Profile" -> TODO()// Replace with ProfileScreen()
//                "Task List" -> AddTaskScreen(
//                    viewModel = taskViewModel,
//                    onTaskSaved = {
//                        navController.navigate("dashboard_screen") {
//                            popUpTo("add_task") { inclusive = true }
//                        }
//                    }
//                ) // Replace with AddTaskScreen()
                "Settings" -> TODO() // Replace with SettingsScreen()
                "Log out"->  HomeScreen(modifier = Modifier, onLoginSuccess = {
                    navController.navigate("dashboard_screen") {
                        popUpTo("home_screen") { inclusive = true }
                    }
                })

            }
        }


    }
}
@Composable
fun DrawerItem(
    title: String,
    selected: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    onClick: () -> Unit
){
    val isSelected = selected == title
    val background = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    else Color.Transparent

    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
                scope.launch { drawerState.close() }
            }
            .background(background)
            .padding(16.dp)
    )

}


