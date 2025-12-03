package com.example.taskmunk.features.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmunk.data.Task
import com.example.taskmunk.features.splash.SplashScreen
import com.example.taskmunk.ui.theme.TaskMunkTheme

@Composable
fun TaskCard(task: Task, modifier: Modifier = Modifier, onEventClick: (Task) -> Unit){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{},
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ){
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier= Modifier.weight(1f)
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    TaskMunkTheme {
        TaskCard(
            task = TODO(),
            modifier = TODO(),
            onEventClick = TODO()
        )
    }
}

