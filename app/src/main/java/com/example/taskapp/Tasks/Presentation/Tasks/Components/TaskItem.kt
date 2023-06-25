package com.example.taskapp.Tasks.Presentation.Tasks.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.Tasks.Domain.Model.Task
import com.example.taskapp.Tasks.Presentation.AddEditTask.AddEditTaskViewModel
import kotlinx.coroutines.launch

var taskCheck = false;

@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier,
    viewModel: AddEditTaskViewModel = hiltViewModel(),
    onDeleteClick: () -> Unit,
   /* onStarClick: () -> Unit*/
    ) {
        Box(
            modifier = modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
        ){
            Column(
                modifier = Modifier

                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(end = 32.dp)
            ) {
                Text(
                    text = task.Title,

                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = task.Desc,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(

                    text = updTime(task.Time/1000),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
          /*  IconButton(
                onClick = onStarClick,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Добавить в избранное",
                    tint = Color.Black
                )
            }

*/

            val checked = remember { mutableStateOf(task.Favorites) }
            IconToggleButton(

                checked = checked.value,
                modifier = Modifier.align(Alignment.TopEnd),
                onCheckedChange = {
                    checked.value = it;
                    taskCheck = it;

                }
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Избранное",
                    tint = if (checked.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                )
            }

            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить задачу",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
}

fun updTime(second: Long): String {
    var hours = (second % 86400) / 3600;
    var minutes = (second % 3600) / 60;
    var sec = (second % 60)
    if (sec < 10 && minutes < 10) {
        return "$hours:0$minutes:0$sec";
    }
    if (minutes < 10) {
        return "$hours:0$minutes:$sec";
    }
    if (sec < 10) {
        return "$hours:$minutes:0$sec";
    }
    return "$hours:$minutes:$sec";
}