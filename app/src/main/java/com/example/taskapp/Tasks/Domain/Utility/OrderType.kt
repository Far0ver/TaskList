package com.example.taskapp.Tasks.Domain.Utility

sealed class OrderType{
    object Descending: OrderType()
    object Ascending: OrderType()
}
