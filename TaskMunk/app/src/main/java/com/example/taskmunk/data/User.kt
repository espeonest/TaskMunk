package com.example.taskmunk.data

data class User (
    val id: String,
    val password: String,
    val username: String
) {
    constructor() : this("id", "username", "password")
}