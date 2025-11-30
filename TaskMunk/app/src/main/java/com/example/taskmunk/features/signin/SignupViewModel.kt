package com.example.taskmunk.features.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    // need database stuff

    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun onFirstNameChanged(newValue: String){
        firstName = newValue
    }

    fun onLastNameChanged(newValue: String){
        lastName = newValue
    }

    fun onPasswordChanged(newValue: String){
        password = newValue
    }

    fun onEmailChanged(newValue: String){
        email = newValue
    }

    fun onSignupClicked(){

    }

    // validation stuff needed
}