package com.example.taskmunk.features.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterStatusSection(viewModel: DashboardViewModel){

    LazyRow(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        //Display all status types from the dashboard viewmodel
        items(viewModel.statusOptions){ status ->
            FilterChip(
                selected = viewModel.selectedStatus.contains(status),
                onClick = {viewModel.toggleStatusOptions(status) },
                label = {Text(status)},
                modifier = Modifier
                    .height(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}