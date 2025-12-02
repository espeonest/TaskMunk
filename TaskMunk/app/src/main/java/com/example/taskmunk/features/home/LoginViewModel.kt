package com.example.taskmunk.features.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskmunk.validation.ValidationResult

class LoginViewModel : ViewModel() {
    // validation message
    var message by mutableStateOf<String?>(null)
    // user login variables
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    // home screen field state management functions
    fun onEmailChanged(newValue: String){
        email = newValue
    }
    fun onPasswordChanged(newValue: String){
        password = newValue
    }

    // login validation and redirection
    fun onLoginClicked(){
        message = null

        val validations = listOf(
            validateEmail(),
            validatePassword()
        )

        val firstError = validations.firstOrNull() {it is ValidationResult.Error}

        if(firstError is ValidationResult.Error){
            message = firstError.message
        } else {
            // add redirection and stuff :)
        }
    }

    // input validations
    fun validateEmail(): ValidationResult{
        return if(email.isEmpty()) ValidationResult.Error("Please enter a valid email.")
        else ValidationResult.Success
    }
    fun validatePassword(): ValidationResult{
        return if(password.isEmpty()) ValidationResult.Error("Password cannot be empty.")
        else ValidationResult.Success
    }
}