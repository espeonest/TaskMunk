package com.example.taskmunk.features.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmunk.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskForm(
    task: Task,
    onTaskChange: (Task) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Task Details",
                style = MaterialTheme.typography.headlineMedium,
            )

            OutlinedTextField(
                value = task.title,
                onValueChange = { onTaskChange(task.copy(title = it)) },
                label = { Text("Task Title") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = task.description,
                onValueChange = { onTaskChange(task.copy(description = it)) },
                label = { Text("Description") },
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = task.dueDate,
                onValueChange = { onTaskChange(task.copy(dueDate = it)) },
                label = { Text("Due Date") },
                trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            SimpleDropdown(
                label = "Priority",
                options = listOf("High", "Medium", "Low"),
                selectedOption = task.priority,
                onOptionSelected = { onTaskChange(task.copy(priority = it)) }
            )

            SimpleDropdown(
                label = "Category",
                options = listOf("Work", "Personal", "Assignment"),
                selectedOption = task.category,
                onOptionSelected = { onTaskChange(task.copy(category = it)) }
            )

            SimpleDropdown(
                label = "Status",
                options = listOf("To Do", "In Progress", "Completed"),
                selectedOption = task.status,
                onOptionSelected = { onTaskChange(task.copy(status = it)) }
            )

            SimpleDropdown(
                label = "Reminder",
                options = listOf("None", "1 hour before", "1 day before"),
                selectedOption = task.reminder,
                onOptionSelected = { onTaskChange(task.copy(reminder = it)) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDropdown(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOption,
            onValueChange = {},
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskFormPreview() {
    TaskForm(Task(), {})
}

