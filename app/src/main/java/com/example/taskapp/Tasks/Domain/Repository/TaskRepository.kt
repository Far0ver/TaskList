package com.example.taskapp.Tasks.Domain.Repository

import com.example.taskapp.Tasks.Domain.Model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task?

    suspend fun insertTask(Task: Task)

    suspend fun deleteTask(Task: Task)
}