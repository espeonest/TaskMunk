package com.example.taskmunk.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmunk.R
import com.example.taskmunk.ui.theme.TaskMunkTheme
import kotlinx.coroutines.delay

//Splash Screen with App Logo, title, and slogan.  Auto load and navigate to next screen through app Nav.
@Composable
fun SplashScreen(onTimeout: () -> Unit){
    LaunchedEffect(true) {
        //Delay login screen by 3 seconds
        delay(3000)
        onTimeout()
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimary)

            Spacer(modifier = Modifier.height(30.dp))

            Text(stringResource(R.string.app_slogan),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary)

            Spacer(modifier = Modifier.height(12.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    TaskMunkTheme {
     SplashScreen(onTimeout = {})
    }
}
