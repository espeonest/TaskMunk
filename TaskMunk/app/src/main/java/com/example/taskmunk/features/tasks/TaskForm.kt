package com.example.taskmunk.features.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmunk.R
import com.example.taskmunk.utils.SimpleDropdown

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
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.task_form_header),
                style = MaterialTheme.typography.headlineMedium,
            )

            OutlinedTextField(
                value = viewModel.selectedTask.title,
                onValueChange = { viewModel.onTitleChange(it) },
                label = { Text(stringResource(R.string.task_title_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.selectedTask.description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                label = { Text(stringResource(R.string.description_label)) },
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.selectedTask.dueDate,
                onValueChange = { viewModel.onDueDateChange(it) },
                label = { Text(stringResource(R.string.due_date_label)) },
                trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            SimpleDropdown(
                label = stringResource(R.string.priority_label),
                options = priorityOptions,
                selectedOption = viewModel.selectedTask.priority,
                onOptionSelected = { viewModel.onPriorityChange(it) }
            )

            SimpleDropdown(
                label = stringResource(R.string.category_label),
                options = categoryOptions,
                selectedOption = viewModel.selectedTask.category,
                onOptionSelected = { viewModel.onCategoryChange(it) }
            )

            SimpleDropdown(
                label = stringResource(R.string.status_label),
                options = statusOptions,
                selectedOption = viewModel.selectedTask.status,
                onOptionSelected = { viewModel.onStatusChange(it) }
            )

            SimpleDropdown(
                label = stringResource(R.string.reminder_label),
                options = reminderOptions,
                selectedOption = viewModel.selectedTask.reminder,
                onOptionSelected = { viewModel.onReminderChange(it) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskFormPreview() {
    val viewModel: TaskViewModel = viewModel()
    TaskForm(viewModel)
}

