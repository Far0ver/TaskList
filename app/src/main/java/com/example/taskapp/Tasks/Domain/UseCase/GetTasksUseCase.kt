package com.example.taskapp.Tasks.Domain.UseCase

import com.example.taskapp.Tasks.Domain.Model.Task
import com.example.taskapp.Tasks.Domain.Repository.TaskRepository
import com.example.taskapp.Tasks.Domain.Utility.OrderType
import com.example.taskapp.Tasks.Domain.Utility.TaskOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasks(
    private val repository: TaskRepository
){
    operator fun invoke(
        taskOrder: TaskOrder = TaskOrder.Date(OrderType.Descending)
    ): Flow<List<Task>>{
        return repository.getTasks().map{ Tasks ->
            when (taskOrder.orderType){
                is OrderType.Ascending ->{
                    when(taskOrder){
                        is TaskOrder.Date ->Tasks.sortedBy { it.Time }
                        is TaskOrder.Title -> Tasks.sortedBy { it.Title }
                    }
                }
                is OrderType.Descending -> {
                    when(taskOrder){
                        is TaskOrder.Date ->Tasks.sortedByDescending { it.Time }
                        is TaskOrder.Title -> Tasks.sortedByDescending { it.Title }
                    }
                }
            }
        }
    }
}