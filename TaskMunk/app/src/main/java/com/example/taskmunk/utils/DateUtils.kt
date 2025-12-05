package com.example.taskmunk.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun getDateString(calendar: Calendar = Calendar.getInstance()): String {
        return formatter.format(calendar.time)
    }

    fun parseDateString(dateString: String): Calendar {
        if (dateString.isBlank()) return Calendar.getInstance()
        val date = formatter.parse(dateString) ?: return Calendar.getInstance()
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }