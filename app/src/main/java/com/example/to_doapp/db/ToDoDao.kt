package com.example.to_doapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.to_doapp.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDo ORDER BY createdAt DESC")
    fun getAllToDo() : LiveData<List<ToDo>>

    @Insert
    fun addToDo(toDo : ToDo)

    @Query("DELETE FROM ToDo WHERE id = :id")
    fun deleteToDo(id : Int)
}
