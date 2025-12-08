package com.example.taskmunk.features.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmunk.R
import com.example.taskmunk.utils.getDateString
import com.example.taskmunk.utils.parseDateString
import java.util.Calendar

// A reusable form for creating and editing tasks
@Composable
fun TaskForm(
    viewModel: TaskViewModel
) {
    // View model options lists are stored as string resources, so they need to be converted to strings in the composable
    val priorityOptions = viewModel.priorityOptions.map { stringResource(it) }
    val categoryOptions = viewModel.categoryOptions.map { stringResource(it) }
    val statusOptions = viewModel.statusOptions.map { stringResource(it) }
    val reminderOptions = viewModel.reminderOptions.map { stringResource(it) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.task_form_header),
                style = MaterialTheme.typography.headlineMedium,
            )

            OutlinedTextField(
                value = viewModel.titleInput,
                onValueChange = { viewModel.onTitleChange(it) },
                label = { Text(stringResource(R.string.task_title_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.descriptionInput,
                onValueChange = { viewModel.onDescriptionChange(it) },
                label = { Text(stringResource(R.string.description_label)) },
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SimpleDatePicker(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.due_date_label),
                    selectedDate = viewModel.dueDateInput,
                    onDateSelected = { viewModel.onDueDateChange(it) }
                )

                SimpleDropdown(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.priority_label),
                    options = priorityOptions,
                    selectedOption = viewModel.priorityInput,
                    onOptionSelected = { viewModel.onPriorityChange(it) }
                )
            }

            SimpleDropdown(
                label = stringResource(R.string.category_label),
                options = categoryOptions,
                selectedOption = viewModel.categoryInput,
                onOptionSelected = { viewModel.onCategoryChange(it) }
            )

            SimpleDropdown(
                label = stringResource(R.string.status_label),
                options = statusOptions,
                selectedOption = viewModel.statusInput,
                onOptionSelected = { viewModel.onStatusChange(it) }
            )

            SimpleDropdown(
                label = stringResource(R.string.reminder_label),
                options = reminderOptions,
                selectedOption = viewModel.reminderInput,
                onOptionSelected = { viewModel.onReminderChange(it) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDropdown(
    modifier: Modifier = Modifier.fillMaxWidth(),
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var persistentSelection by remember { mutableStateOf(selectedOption) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = persistentSelection,
            onValueChange = {},
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    type = MenuAnchorType.PrimaryEditable,
                    enabled = true
                )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        persistentSelection = option
                        onOptionSelected(persistentSelection)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SimpleDatePicker(modifier: Modifier = Modifier.fillMaxWidth(), label: String, selectedDate: String, onDateSelected: (String) -> Unit) {
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

    Box(modifier) {
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

@Preview(showBackground = true)
@Composable
fun TaskFormPreview() {
    val viewModel: TaskViewModel = viewModel()
    TaskForm(viewModel)
}

