package com.example.taskmunk.data

import com.google.firebase.firestore.DocumentId

data class User (
    @DocumentId
    val documentId: String,
    val password: String,
    val username: String
) {
    constructor() : this("id", "username", "password")
}