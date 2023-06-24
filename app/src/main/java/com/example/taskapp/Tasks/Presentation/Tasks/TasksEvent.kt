package com.example.taskapp.Tasks.Presentation.Tasks

import com.example.taskapp.Tasks.Domain.Model.Task
import com.example.taskapp.Tasks.Domain.Utility.TaskOrder

sealed class TasksEvent{
    data class Order(val taskOrder:TaskOrder): TasksEvent()
    data class DeleteTask(val task:Task): TasksEvent()
    object RestoreTask: TasksEvent()
    object ToggleOrderSection: TasksEvent()
}
