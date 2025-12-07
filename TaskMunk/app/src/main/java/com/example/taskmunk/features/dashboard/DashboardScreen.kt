package com.example.taskmunk.features.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.taskmunk.R
import com.example.taskmunk.data.Task

@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel,
    onTaskSelected: (Task) -> Unit
) {
    Scaffold(
        topBar = { DashboardScreenTopBar() },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.surfaceContainer
        )

        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    //Search Bar to search for certain tasks by their title
                    TextField(
                        value = dashboardViewModel.searchBarText,
                        onValueChange = { dashboardViewModel.onSearchBarTextChanged(it) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search...",
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(start = 2.dp)
                            )
                        },
                        placeholder = { Text(text = stringResource(R.string.search_placeholder)) },
                        singleLine = true,
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f),
                    )
                    Spacer(modifier = Modifier.width(18.dp))

                    //Sort by Status
                    FilterStatusSection(viewModel = dashboardViewModel)
                }
                //If there are no tasks yet display a message
                if (dashboardViewModel.filterTasks.isEmpty()) {

                    Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                        horizontalArrangement = Arrangement.Center){
                        Text(text = stringResource(R.string.empty_dashboard_msg),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground)
                    }
                } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                ) {
                    items(dashboardViewModel.filterTasks) { task ->
                        TaskCard(
                            task = task,
                            onTaskClick = onTaskSelected
                        )
                    }
                }
            }
        }
    }
}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreenTopBar() {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = stringResource(R.string.app_name),
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
    )
}

