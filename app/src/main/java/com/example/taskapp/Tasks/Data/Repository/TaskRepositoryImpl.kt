package com.example.taskapp.Tasks.Data.Repository

import com.example.taskapp.Tasks.Data.DataSource.TaskDAO
import com.example.taskapp.Tasks.Domain.Model.Task
import com.example.taskapp.Tasks.Domain.Repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDAO
): TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(Task: Task) {
        dao.insertTask(Task)
    }

    override suspend fun deleteTask(Task: Task) {
        dao.deleteTask(Task)
    }
}