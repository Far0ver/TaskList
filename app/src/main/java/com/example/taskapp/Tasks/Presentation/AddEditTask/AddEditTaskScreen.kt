package com.example.taskapp.Tasks.Presentation.AddEditTask

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapp.Tasks.Domain.Model.Task
import com.example.taskapp.Tasks.Presentation.AddEditTask.Components.TransparentHintTextField
import com.example.taskapp.Tasks.Presentation.Tasks.Components.taskCheck
import kotlinx.coroutines.flow.collectLatest
import com.example.taskapp.Tasks.Presentation.Tasks.TasksScreen

var taskF = false;

@Composable
fun AddEditTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val titleState = viewModel.taskTitle.value
    val contentState = viewModel.taskContent.value


    val scaffoldState = rememberScaffoldState()



    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is AddEditTaskViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditTaskViewModel.UiEvent.SaveTask -> {
                    navController.navigateUp()
                }
            }
        }
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditTaskEvent.SaveTask)
                },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Сохранить задачу"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues = it)
        ){

            }

           /* IconToggleButton(

                checked = checked.value,

                onCheckedChange = {
                    checked.value = it;
                    taskF = checked.value

                }
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Избранное",
                    tint = if (checked.value) Color(0xFFEC407A) else Color(0xFFB0BEC5)
                )
            }*/
            TransparentHintTextField(
                text = titleState.text,

                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditTaskEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditTaskEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditTaskEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditTaskEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxHeight()
            )
        }


    }


