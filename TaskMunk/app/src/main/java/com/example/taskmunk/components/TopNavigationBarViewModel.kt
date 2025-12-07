package com.example.taskmunk.components

import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TopNavigationBarViewModel: ViewModel() {
        var drawerState = DrawerValue.Closed
        private set

    var selectedScreen by mutableStateOf("Dashboard")
        private set

    fun onScreenSelected(screen: String) {
        selectedScreen = screen
    }
}