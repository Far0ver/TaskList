package com.example.taskapp.Tasks.Domain.Utility

sealed class TaskOrder (val orderType: OrderType){
    class Date(orderType: OrderType): TaskOrder(orderType)
    class Title(orderType: OrderType): TaskOrder(orderType)

    fun copy(orderType: OrderType): TaskOrder{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }
    }
}
