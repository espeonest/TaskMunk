package com.example.taskmunk.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// Helper functions for parsing and formatting dates
val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

// Get a date as a string
// Defaults to today's date
fun getDateString(calendar: Calendar = Calendar.getInstance()): String {
    return formatter.format(calendar.time)
}

// Parse a date string into a Calendar object
fun parseDateString(dateString: String): Calendar {
    if (dateString.isBlank()) return Calendar.getInstance()
    val date = formatter.parse(dateString) ?: return Calendar.getInstance()
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar
}