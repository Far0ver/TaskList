package com.example.taskapp.Tasks.Domain.UseCase

data class TaskUseCases(
    val getTasks: GetTasks,
    val deleteTask: DeleteTaskCase,
    val addTask: AddTask,
    val getTask: GetTask
)
