package com.example.taskmunk.features.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmunk.data.User
import com.example.taskmunk.validation.ValidationResult
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // validation message
    var message by mutableStateOf<String?>(null)
    // database
    val db = Firebase.firestore
    // user login variables
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var signinSuccess by mutableStateOf(false)

    // home screen field state management functions
    fun onUsernameChanged(newValue: String){
        username = newValue
    }
    fun onPasswordChanged(newValue: String){
        password = newValue
    }

    // login validation and redirection
    fun onLoginClicked(){
        message = null

        val validations = listOf(
            validateUsername(),
            validatePassword()
        )

        val firstError = validations.firstOrNull() {it is ValidationResult.Error}

        if(firstError is ValidationResult.Error){
            message = firstError.message
            signinSuccess = false
        } else {
            validateLogin()
        }
    }

    // input validations
    fun validateUsername(): ValidationResult{
        return if(username.isEmpty()) ValidationResult.Error("Please enter a valid email.")
        else ValidationResult.Success
    }
    fun validatePassword(): ValidationResult{
        return if(password.isEmpty()) ValidationResult.Error("Password cannot be empty.")
        else ValidationResult.Success
    }

    // sign-in validation
    fun validateLogin() {
        db.collection("users").get()
            .addOnSuccessListener { result ->
                var check = false
                for (user in result) {
                    val tempUser = user.toObject<User>()
                    if (tempUser.username == username &&
                        tempUser.password == password
                    ) {
                        check = true
                    }
                }
                if(check){
                    loginSuccess()
                } else {
                    signinSuccess = false
                    loginFailure()
                }
            }
    }

    fun loginSuccess(){
        signinSuccess = true
    }

    fun loginFailure(){
        message = "Credentials invalid, unable to sign in."
    }
}