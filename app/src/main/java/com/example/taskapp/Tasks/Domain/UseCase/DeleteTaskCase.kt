package com.example.taskapp.Tasks.Domain.UseCase

import com.example.taskapp.Tasks.Domain.Model.Task
import com.example.taskapp.Tasks.Domain.Repository.TaskRepository

class DeleteTaskCase(
    private val repository: TaskRepository
){
    suspend operator fun invoke(Task: Task){
        repository.deleteTask(Task)
    }
}
