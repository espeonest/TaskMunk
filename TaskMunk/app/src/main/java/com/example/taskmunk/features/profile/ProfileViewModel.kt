package com.example.taskmunk.features.profile

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmunk.data.User
import com.example.taskmunk.data.UserDataStore
import com.example.taskmunk.validation.ValidationResult
import com.google.firebase.Firebase
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    // declare variables
    var message by mutableStateOf<String?>(null)
    var username by mutableStateOf("")
        private set
    var oldPassword by mutableStateOf("")
        private set
    var newPassword by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    // database & preferences file
    val db = Firebase.firestore
    val userDataStore = UserDataStore(application)
    // read saved username from datastore
    var _savedUsername = MutableStateFlow("")
    var savedUsername: StateFlow<String> = _savedUsername
    fun loadName() {
        // load from datastore
        viewModelScope.launch {
            username = userDataStore.intakeUsername.first()
            _savedUsername.value = username
        }
    }

    // state management functions
    fun oldPasswordChanged(password: String){
        oldPassword = password
    }
    fun newPasswordChanged(password: String){
        newPassword = password
    }
    fun confirmPasswordChanged(password: String){
        confirmPassword = password
    }
    // on button pressed, validate inputs and try to update password.
    fun onUpdatePressed(){
        message = null
        val validations = listOf(
            validateNotEmpty(),
            validateCurrentPassword(),
            validatePasswordMatch()
        )

        val firstError = validations.firstOrNull() {it is ValidationResult.Error}

        if(firstError is ValidationResult.Error){
            message = firstError.message
        }
    }

    // field validation
    var userId by mutableStateOf("")
    fun validateNotEmpty() : ValidationResult{
        return if(oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty())
            ValidationResult.Error("Field cannot be left empty.")
        else ValidationResult.Success
    }
    fun validateCurrentPassword(){
        db.collection("users").get()
            .addOnSuccessListener { result ->
                var check = false
                for (user in result) {
                    val tempUser = user.toObject<User>()
                    if (tempUser.username == username &&
                        tempUser.password == oldPassword
                    ) {
                        check = true
                        userId = tempUser.documentId
                        Log.d("TAG", tempUser.documentId)
                    }
                }
                if(check){
                    changeSuccess(userId)
                } else {
                    changeFailure()
                }
            }
    }
    fun validatePasswordMatch() : ValidationResult {
        return if(newPassword!=confirmPassword)
            ValidationResult.Error("Password confirmation does not match")
        else return ValidationResult.Success
    }
    fun changeSuccess(userId : String){
        this.userId = userId
        val userData = hashMapOf(
            "password" to newPassword,
            "username" to username
        )
        db.collection("users").document(userId).set(userData)
        oldPassword = ""
        newPassword = ""
        confirmPassword = ""
        message = "Password has been updated."
    }
    fun changeFailure(){
        if (message.isNullOrEmpty()) message = "Error updating password."
    }
}