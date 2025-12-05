package com.example.taskmunk.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun SimpleDatePicker(label: String, selectedDate: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val selectedDateCalendar = parseDateString(selectedDate)
    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, y, m, d ->
            val newDate = Calendar.getInstance()
            newDate.set(y, m, d)
            val newDateString = getDateString(newDate)
            onDateSelected(newDateString)
        },
        selectedDateCalendar.get(Calendar.YEAR),
        selectedDateCalendar.get(Calendar.MONTH),
        selectedDateCalendar.get(Calendar.DAY_OF_MONTH),
    )

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(Icons.Default.DateRange, contentDescription = label)
            }
        }
    )
}