package com.example.taskmunk.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDatabaseHelper(context: Context):
    SQLiteOpenHelper(context, "tasks", null, 1){

    override fun onCreate(db: SQLiteDatabase?){
        db?.execSQL("""
            CREATE TABLE tasks(
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT,
            description TEXT,
            dueDate TEXT,
            priority TEXT DEFAULT 'Low',
            category TEXT DEFAULT 'Personal',
            status TEXT DEFAULT 'To Do',
            reminder TEXT DEFAULT 'None',
            dateCreated TEXT,
            dateCompleted TEXT
            )
             """.trimIndent())
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        db?.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }
}