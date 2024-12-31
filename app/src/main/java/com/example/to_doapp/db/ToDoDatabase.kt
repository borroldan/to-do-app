package com.example.to_doapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.to_doapp.ToDo

@Database(entities = [ToDo::class], version = 1)
@TypeConverters(Converters::class)
abstract class ToDoDatabase : RoomDatabase() {
    companion object {
        const val NAME = "todo.db"
    }

    abstract fun getToDoDao() : ToDoDao
}
