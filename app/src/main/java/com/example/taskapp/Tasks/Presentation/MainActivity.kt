package com.example.taskapp.Tasks.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskapp.Tasks.Presentation.AddEditTask.AddEditTaskScreen
import com.example.taskapp.Tasks.Presentation.Tasks.TasksScreen
import com.example.taskapp.Tasks.Presentation.Utility.Screen
import com.example.taskapp.ui.theme.TaskAppTheme
import com.google.android.material.tabs.TabItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()

        }
    }
}
@Composable
fun MainScreen() {

    TaskAppTheme {
        Surface (
            color = MaterialTheme.colorScheme.background
        ){
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.TasksScreen.route
            ){
                composable(route = Screen.TasksScreen.route){
                    TasksScreen(navController = navController)
                }
                composable(
                    route = Screen.AddEditTaskScreen.route + "?taskId={taskId}",
                    arguments = listOf(
                        navArgument(
                            name = "taskId"
                        ){
                            type = NavType.IntType
                            defaultValue = -1
                        },
                    )
                ){
                    AddEditTaskScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}



