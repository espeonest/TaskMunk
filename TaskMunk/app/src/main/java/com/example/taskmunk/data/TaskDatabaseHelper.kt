package com.example.taskmunk.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDatabaseHelper(context: Context):
    SQLiteOpenHelper(context, "tasks", null, 1){
        //Create Task Table
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

    //Create - Insert a new Task
    fun insertTask(title: String, description: String, dueDate: String, priority: String, category: String, status: String, reminder: String, dateCreated: String, dateCompleted: String?){
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("description", description)
            put("dueDate", dueDate)
            put("priority", priority)
            put("category", category)
            put("status", status)
            put("reminder", reminder)
            put("dateCreated", dateCreated)
            put("dateCompleted", dateCompleted)
        }
        db.insert("tasks", null, values)
        db.close()
    }

    //Create - Insert a new Task (override using Task object)
    fun insertTask(task: Task) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", task.title)
            put("description", task.description)
            put("dueDate", task.dueDate)
            put("priority", task.priority)
            put("category", task.category)
            put("status", task.status)
            put("reminder", task.reminder)
            put("dateCreated", task.dateCreated)
            put("dateCompleted", task.dateCompleted)
        }
        db.insert("tasks", null, values)
        db.close()
    }

    //Read - Retrieve all tasks
    fun getAllTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tasks", null)
        while (cursor.moveToNext()) {
            tasks.add(
                Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9)
                )
            )
        }
        cursor.close()
        db.close()

        return tasks
    }

    //Update - Modify selected Task
    fun updateTask(id: Int, title: String, description: String, dueDate: String, priority: String, category: String, status: String, reminder: String, dateCreated: String, dateCompleted: String?){
        val db = writableDatabase
        //New values using ContentValues object to store
        val values = ContentValues().apply{
            put("title", title)
            put("description", description)
            put("dueDate", dueDate)
            put("priority", priority)
            put("category", category)
            put("status", status)
            put("reminder", reminder)
            put("dateCreated", dateCreated)
            put("dateCompleted", dateCompleted)
        }
        db.update("tasks", values,"id=?", arrayOf(id.toString()))
        db.close()
    }

    //Update - Modify selected Task (override using Task object)
    fun updateTask(id: Int, task: Task){
        val db = writableDatabase
        //New values using ContentValues object to store
        val values = ContentValues().apply{
            put("title", task.title)
            put("description", task.description)
            put("dueDate", task.dueDate)
            put("priority", task.priority)
            put("category", task.category)
            put("status", task.status)
            put("reminder", task.reminder)
            put("dateCreated", task.dateCreated)
            put("dateCompleted", task.dateCompleted)
        }
        db.update("tasks", values,"id=?", arrayOf(id.toString()))
        db.close()
    }

    //Delete - Remove selected Task
    fun deleteTask(id: Int){
        val db = writableDatabase
        db.delete("tasks", "id=?", arrayOf(id.toString()))
        db.close()
    }
}

