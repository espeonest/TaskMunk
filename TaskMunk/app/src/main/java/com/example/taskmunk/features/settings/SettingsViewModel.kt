package com.example.taskmunk.features.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingsViewModel: ViewModel() {
    var isDarkMode = mutableStateOf(false)
    fun toggleDarkMode() {
        isDarkMode.value = !isDarkMode.value
    }

}