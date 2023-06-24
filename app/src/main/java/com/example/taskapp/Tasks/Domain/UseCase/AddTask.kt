package com.example.taskapp.Tasks.Domain.UseCase

import com.example.taskapp.Tasks.Domain.Model.InvalidTaskException
import com.example.taskapp.Tasks.Domain.Model.Task
import com.example.taskapp.Tasks.Domain.Repository.TaskRepository
import kotlin.jvm.Throws

class AddTask(
    private val repository: TaskRepository
){

    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task){
        if (task.Title.isBlank()){
            throw InvalidTaskException("Название не должно быть быть пустым")
        }
        if (task.Desc.isBlank()){
            throw InvalidTaskException("Описание не должно быть пустым")
        }
        repository.insertTask(task)
    }
}