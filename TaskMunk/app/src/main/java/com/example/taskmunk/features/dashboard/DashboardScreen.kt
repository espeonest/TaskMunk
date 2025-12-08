package com.example.taskmunk.features.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskmunk.R
import com.example.taskmunk.components.DrawerItem
import com.example.taskmunk.components.TopNavigationBarViewModel
import com.example.taskmunk.data.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    navController: NavController,
    dashboardViewModel: DashboardViewModel,
    onTaskSelected: (Task) -> Unit
) {
    val viewModel: TopNavigationBarViewModel = viewModel()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedScreen = viewModel.selectedScreen

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerItem(stringResource(R.string.drawer_dashboard), selectedScreen, scope, drawerState) {
                    viewModel.onScreenSelected("Dashboard")
                    navController.navigate("dashboard_screen") {
                        popUpTo("dashboard_screen") { inclusive = true }
                    }
                }

                DrawerItem(stringResource(R.string.drawer_profile), selectedScreen, scope, drawerState) {
                    viewModel.onScreenSelected("Profile")
                    navController.navigate("profile")
                }
                DrawerItem(stringResource(R.string.drawer_add_task), selectedScreen, scope, drawerState) {
                    viewModel.onScreenSelected("Add Task")
                    navController.navigate("add_task")
                }
                DrawerItem(stringResource(R.string.drawer_settings), selectedScreen, scope, drawerState) {
                    viewModel.onScreenSelected("Settings")
                }
                DrawerItem(stringResource(R.string.drawer_logout), selectedScreen, scope, drawerState) {
                    // Close drawer first
                    scope.launch { drawerState.close() }

                    // Navigate to HomeScreen and clear backstack
                    navController.navigate("home_screen") {
                        popUpTo("dashboard_screen") { inclusive = true }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                DashboardScreenTopBar(
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.surfaceContainer
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = innerPadding.calculateTopPadding())
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)){
                        dashboardViewModel.loadName()
                        Text(stringResource(R.string.welcome_user) + " ${dashboardViewModel._savedUsername.collectAsState().value}",
                            style = MaterialTheme.typography.titleLarge)
                    }
                    //Search Bar to search for certain tasks by their title
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        TextField(
                            value = dashboardViewModel.searchBarText,
                            onValueChange = { dashboardViewModel.onSearchBarTextChanged(it) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            placeholder = { Text(text = stringResource(R.string.search_placeholder)) },
                            singleLine = true,
                            modifier = Modifier
                                .height(50.dp)
                                .weight(1f),
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        //Filter Chips to display task by a certain status
                        FilterStatusSection(viewModel = dashboardViewModel)
                    }

                    // If there are no tasks yet display a message
                    // Empty or List
                    if (dashboardViewModel.filterTasks.isEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.empty_dashboard_msg),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(dashboardViewModel.filterTasks) { task ->
                                TaskCard(
                                    task = task,
                                    onTaskClick = onTaskSelected
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreenTopBar(
    onMenuClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Hamburger icon
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onMenuClick() }
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Logo
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier.size(40.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // App name
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    )
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

