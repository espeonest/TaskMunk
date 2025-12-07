package com.example.taskmunk.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("taskmunk_prefs")

class UserDataStore (private val context: Context){
    companion object {
        val USERNAME = stringPreferencesKey("username")
    }

    // save username, password isn't important
    suspend fun saveUsername(username: String){
        context.dataStore.edit { prefs ->
            prefs[USERNAME] = username
        }
    }

    // read username
    val intakeUsername: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[USERNAME] ?: ""
    }
}