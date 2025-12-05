package com.example.taskmunk.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun SimpleDatePicker(label: String, selectedDate: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    var persistentSelection by remember { mutableStateOf(selectedDate) }
    val calendar = parseDateString(persistentSelection)

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, y, m, d ->
            val newDate = Calendar.getInstance()
            newDate.set(y, m, d)
            persistentSelection = getDateString(newDate)
            onDateSelected(persistentSelection)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH),
    )

    Box(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = persistentSelection,
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

        // Overlay the text field so clicking anywhere shows the date picker dialog
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { datePickerDialog.show() }
        )
    }
}