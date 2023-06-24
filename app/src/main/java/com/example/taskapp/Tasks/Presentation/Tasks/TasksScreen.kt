package com.example.taskapp.Tasks.Presentation.Tasks


import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import androidx.compose.material.*
import androidx.compose.material.icons.filled.Sort
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskapp.Tasks.Presentation.Tasks.Components.OrderSection
import com.example.taskapp.Tasks.Presentation.Tasks.Components.TaskItem
import com.example.taskapp.Tasks.Presentation.Utility.Screen
import kotlinx.coroutines.launch

@Composable
fun TasksScreen (
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditTaskScreen.route)
                },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Добавить задачу")
            }
        },
        scaffoldState = scaffoldState
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(

                    text = " Ваши задачи",

                    lineHeight = 32.sp,
                    style = MaterialTheme.typography.headlineLarge
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(TasksEvent.ToggleOrderSection)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Сортировка"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    taskOrder = state.taskOrder,
                    onOrderChange =  {
                        viewModel.onEvent(TasksEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.tasks){ task ->
                    TaskItem(
                        task = task,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditTaskScreen.route +
                                            "?taskId=${task.id}"
                                )
                            }
                            .background(MaterialTheme.colorScheme.primary),
                        onDeleteClick = {
                            viewModel.onEvent(TasksEvent.DeleteTask(task))
                            scope.launch{
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Задача удалена",
                                    actionLabel = "Отменить"
                                )
                                if (result == SnackbarResult.ActionPerformed){
                                    viewModel.onEvent(TasksEvent.RestoreTask)
                                }
                            }
                        }

                    /*onStarClick = {
                            viewModel.onEvent(TasksEvent.DeleteTask(task))
                            scope.launch{
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Задача удалена",
                                    actionLabel = "Отменить"
                                )
                                if (result == SnackbarResult.ActionPerformed){
                                    viewModel.onEvent(TasksEvent.RestoreTask)
                                }
                            }}*/



                    )
                    Spacer(modifier = Modifier.height(16.dp))


                }






            }
        }
    }
}