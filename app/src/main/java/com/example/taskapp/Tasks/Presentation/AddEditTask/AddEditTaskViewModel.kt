package com.example.taskapp.Tasks.Presentation.AddEditTask

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.Tasks.Domain.Model.InvalidTaskException
import com.example.taskapp.Tasks.Domain.Model.Task
import com.example.taskapp.Tasks.Domain.UseCase.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _taskTitle = mutableStateOf(TaskTextFieldState(
        hint = "Введите название"
    ))
    val taskTitle: State<TaskTextFieldState> = _taskTitle

    private val _taskContent = mutableStateOf(TaskTextFieldState(
        hint = "Введите описание"
    ))
    val taskContent: State<TaskTextFieldState> = _taskContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId: Int? = null

    init{
        savedStateHandle.get<Int>("taskId")?.let{taskId->
            if(taskId != -1){
                viewModelScope.launch{
                    taskUseCases.getTask(taskId)?.also{ task ->
                        currentTaskId = task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.Title,
                            isHintVisible = false
                        )
                        _taskContent.value = _taskContent.value.copy(
                            text = task.Desc,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent){
        when(event){
            is AddEditTaskEvent.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            taskTitle.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.EnteredContent -> {
                _taskContent.value = _taskContent.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeContentFocus -> {
                _taskContent.value = _taskContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _taskContent.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch{
                    try{
                        taskUseCases.addTask(
                            Task(
                                Title = taskTitle.value.text,
                                Desc = taskContent.value.text,
                                Time = System.currentTimeMillis(),
                                id = currentTaskId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch(e: InvalidTaskException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message?: "Невозможно сохранить задачу"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveTask: UiEvent()
    }
}