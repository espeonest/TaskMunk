package com.example.taskmunk.features.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.taskmunk.R
import com.example.taskmunk.data.Task
import com.example.taskmunk.navigation.TopBar

@Composable
fun TaskDetailsScreen(
    viewModel: TaskViewModel,
    onEditSelected: () -> Unit,
    onTaskDeleted: (Task) -> Unit,
    onBackClick: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar("Task Details", false, onBackClick) },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding()),
            color = MaterialTheme.colorScheme.surfaceContainer
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = viewModel.selectedTask.title,
                                style = MaterialTheme.typography.headlineMedium,
                            )
                            SpacerBar()

                            DetailRow(
                                label = stringResource(R.string.description_label),
                                value = viewModel.selectedTask.description
                            )
                            DetailRow(
                                label = stringResource(R.string.status_label),
                                value = viewModel.selectedTask.status
                            )
                            SpacerBar()
                            DetailRow(
                                label = stringResource(R.string.date_created_label),
                                value = viewModel.selectedTask.dateCreated
                            )
                            DetailRow(
                                label = stringResource(R.string.due_date_label),
                                value = viewModel.selectedTask.dueDate
                            )
                            if (viewModel.selectedTask.dateCompleted != null) {
                                DetailRow(
                                    label = stringResource(R.string.date_completed_label),
                                    value = viewModel.selectedTask.dateCompleted!!
                                )
                            }
                            SpacerBar()
                            DetailRow(
                                label = stringResource(R.string.priority_label),
                                value = viewModel.selectedTask.priority
                            )
                            DetailRow(
                                label = stringResource(R.string.category_label),
                                value = viewModel.selectedTask.category
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                OutlinedButton(
                                    onClick = { showDeleteDialog = true },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        stringResource(R.string.delete_task_button),
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Button(
                                    onClick = onEditSelected,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        stringResource(R.string.edit_task_button),
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }
                    }
                }
            }
            if (showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    title = {
                        Text(
                            text = stringResource(R.string.delete_task_title),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(R.string.delete_task_message),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    confirmButton = {
                        Button(onClick = {
                            showDeleteDialog = false
                            viewModel.deleteTask(onTaskDeleted)
                        }) {
                            Text(
                                text = stringResource(R.string.delete_popup_button),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDeleteDialog = false }) {
                            Text(
                                text = stringResource(R.string.cancel_popup_button),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun SpacerBar(
    paddingValues: PaddingValues = PaddingValues(0.dp, 8.dp),
    height: Int = 4,
    width: Int = 200,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Spacer(
        modifier = Modifier
            .padding(paddingValues)
            .width(width.dp)
            .height(height.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color)
    )
}