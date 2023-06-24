package com.example.taskapp.Tasks.Domain.UseCase

import com.example.taskapp.Tasks.Domain.Model.Task
import com.example.taskapp.Tasks.Domain.Repository.TaskRepository

class GetTask (
    private val repository: TaskRepository
){
    suspend operator fun invoke(id: Int): Task? {
        return repository.getTaskById(id)
    }
}