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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmunk.data.Task
import com.example.taskmunk.ui.theme.TaskMunkTheme



@Composable
fun TaskCard(task: Task, modifier: Modifier = Modifier, onEventClick: (Task) -> Unit){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{},
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.large,
        //style = MaterialTheme.colorScheme.
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = task.status,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Calendar",
                    modifier = Modifier
                        .size(16.dp)
                    )

                Spacer(modifier = Modifier.width(4.dp))
                Text(text = task.dueDate, style = MaterialTheme.typography.bodyMedium)
            }
                Spacer(modifier = Modifier.width(16.dp))

                Text(text = task.priority, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.width(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Default.Label,
                        contentDescription = "Calendar",
                        modifier = Modifier
                            .size(16.dp),
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = task.category, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
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
            onEventClick = {}
        )
    }
}

