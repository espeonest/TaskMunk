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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmunk.R

@Composable
fun TaskDetailsScreen(
    viewModel: TaskViewModel, onEditSelected: () -> Unit, onTaskDeleted: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surfaceContainer
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
                            onClick = { viewModel.deleteTask(onTaskDeleted) },
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

@Preview(showBackground = true)
@Composable
fun TaskDetailsScreenPreview() {
    val viewModel: TaskViewModel = viewModel()
    TaskDetailsScreen(viewModel, {}, {})
}