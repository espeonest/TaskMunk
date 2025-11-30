package com.example.taskmunk.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun GetCurrentDate(): Date {
    return Calendar.getInstance().time
}

fun GetFormattedDate(date: Date = GetCurrentDate()): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(date)
}
