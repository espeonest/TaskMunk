package com.example.taskmunk.features.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmunk.R

@Composable
fun EditTaskScreen(
    viewModel: TaskViewModel,
    onTaskSaved: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TaskForm(viewModel)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.saveTask(onTaskSaved) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_task_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditTaskScreenPreview() {
    val viewModel: TaskViewModel = viewModel()
    EditTaskScreen(viewModel, {})
}