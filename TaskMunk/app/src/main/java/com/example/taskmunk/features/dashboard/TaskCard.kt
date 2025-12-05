package com.example.taskmunk.features.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Label
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmunk.data.Task
import com.example.taskmunk.ui.theme.TaskMunkTheme


@Composable
fun TaskCard(task: Task, modifier: Modifier = Modifier, onTaskClick: (Task) -> Unit){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{ onTaskClick(task) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.large,
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                //TODO: Create styling for badge style
                Text(
                    text = task.status,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Calendar",
                    modifier = Modifier
                        .size(16.dp)
                    )

                Spacer(modifier = Modifier.width(4.dp))

                Text(text = task.dueDate, style = MaterialTheme.typography.bodyLarge)
            }
                Spacer(modifier = Modifier.width(38.dp))

                //TODO:Priority level dot color changing?
                Text(text = task.priority, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.width(38.dp))

                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Default.Label,
                        contentDescription = "Tag",
                        modifier = Modifier
                            .size(16.dp),
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(text = task.category, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskCardScreenPreview() {
    TaskMunkTheme {
        TaskCard(
            task = Task(
                id = "sample",
                title = "Design Wireframes",
                status = "In progress",
                dueDate = "Dec 07, 2025",
                priority  = "High",
                category = "Assignment"
            ),
            modifier = Modifier,
            onTaskClick = {}
        )
    }
}

