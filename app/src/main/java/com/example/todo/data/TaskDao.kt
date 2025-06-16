package com.example.todo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    suspend fun findAll(): List<Task>

    @Insert
    suspend fun save(data: Task)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun delete( id:Int)

    @Update
    suspend fun update(dao: Task)

}