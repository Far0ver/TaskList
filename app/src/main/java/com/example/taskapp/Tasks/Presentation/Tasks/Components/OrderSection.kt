package com.example.taskapp.Tasks.Presentation.Tasks.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapp.Tasks.Domain.Utility.OrderType
import com.example.taskapp.Tasks.Domain.Utility.TaskOrder

@Composable
fun OrderSection (
    modifier: Modifier = Modifier,
    taskOrder: TaskOrder = TaskOrder.Date(OrderType.Descending),
    onOrderChange: (TaskOrder) -> Unit
){
    Column(
        modifier = modifier
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Название",
                selected = taskOrder is TaskOrder.Title,
                onSelect = { onOrderChange(TaskOrder.Title(taskOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Дата",
                selected = taskOrder is TaskOrder.Date,
                onSelect = { onOrderChange(TaskOrder.Date(taskOrder.orderType)) }
            )
        }
            Spacer(modifier = Modifier.height(1.dp))

    Row (
            modifier = Modifier.fillMaxWidth()
        ){
        DefaultRadioButton(
            text = "По возрастанию",
            selected = taskOrder.orderType is OrderType.Ascending,
            onSelect = {
                onOrderChange(taskOrder.copy(OrderType.Ascending)) }
        )
            Spacer(modifier = Modifier.width(8.dp))
        DefaultRadioButton(
                text = "По убыванию",
                selected = taskOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(taskOrder.copy(OrderType.Descending)) }
            )
        }

    }

}