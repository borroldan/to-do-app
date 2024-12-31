package com.example.to_doapp

import android.app.Application
import androidx.room.Room
import com.example.to_doapp.db.ToDoDatabase

class MainApplication : Application() {
    companion object {
        lateinit var toDoDatabase: ToDoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        toDoDatabase = Room.databaseBuilder(
            applicationContext,
            ToDoDatabase::class.java,
            ToDoDatabase.NAME
        ).build()
    }
}