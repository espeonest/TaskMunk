package com.example.taskmunk.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf("dashboard_screen","calendar")
    val icons = listOf(Icons.Default.Home, Icons.Default.DateRange)
    val labels = listOf("dashboard", "calendar")
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary){
        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentRoute == item,
                onClick = { navController.navigate(item) },
                icon = { Icon(icons[index], contentDescription = labels[index]) },
                label = { Text(
                    text = labels[index],
                    style = MaterialTheme.typography.headlineSmall ,//font size define here

                )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,   // icon color when selected
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,   // label color when selected
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f), // icon color when unselected
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)  // label color when unselected
                )
            )
        }
    }
}