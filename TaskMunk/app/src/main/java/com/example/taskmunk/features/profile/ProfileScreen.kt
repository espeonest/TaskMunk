package com.example.taskmunk.features.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmunk.R

@Composable
fun ProfileScreen(modifier: Modifier){
    val viewModel: ProfileViewModel = viewModel()
    viewModel.loadName()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.profile_title), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))
        Text(stringResource(R.string.update_password) + " ${ viewModel.username }", style = MaterialTheme.typography.titleMedium)
        TextField(
            value = viewModel.oldPassword,
            onValueChange = { viewModel.oldPasswordChanged(it) },
            label = { Text(stringResource(R.string.label_old_password)) },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.newPassword,
            onValueChange = { viewModel.newPasswordChanged(it) },
            label = { Text(stringResource(R.string.label_new_password)) },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.confirmPassword,
            onValueChange = { viewModel.confirmPasswordChanged(it) },
            label = { Text(stringResource(R.string.label_confirm_password)) },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {viewModel.onUpdatePressed()},
            colors = ButtonColors(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text(stringResource(R.string.update_button))
        }
        if(!viewModel.message.isNullOrEmpty()){
            Card(
                modifier = Modifier.padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardColors(
                    MaterialTheme.colorScheme.onError,
                    MaterialTheme.colorScheme.error,
                    MaterialTheme.colorScheme.error,
                    MaterialTheme.colorScheme.onError
                ),
                border = BorderStroke(4.dp, MaterialTheme.colorScheme.error)
            ){
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        viewModel.message!!,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}