package com.example.taskmunk.features.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmunk.R

@Composable
fun SignUpScreen(modifier: Modifier = Modifier){
    val viewModel: SignupViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier.size(200.dp)
        )
        Text(stringResource(R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onPrimary)
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = viewModel.firstName,
            onValueChange = { viewModel.onFirstNameChanged(it) },
            label = { Text(stringResource(R.string.label_firstname)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.lastName,
            onValueChange = { viewModel.onLastNameChanged(it) },
            label = { Text(stringResource(R.string.label_lastname)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.email,
            onValueChange = { viewModel.onFirstNameChanged(it) },
            label = { Text(stringResource(R.string.label_password)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.firstName,
            onValueChange = { viewModel.onFirstNameChanged(it) },
            label = { Text(stringResource(R.string.label_password)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        // confirm password field?
        Button(
            onClick = {viewModel.onSignupClicked()},
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 64.dp)
        ) {
            Text("Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp(){
    SignUpScreen()
}