package com.example.taskapp.Tasks.Data.DataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.Tasks.Domain.Model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDataBase: RoomDatabase(){
    abstract val TaskDAO: TaskDAO

    companion object{
        const val DATABASE_NAME = "tasks_db"
    }
}