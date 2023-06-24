package com.example.taskapp.di

import android.app.Application
import androidx.room.Room
import com.example.taskapp.Tasks.Data.DataSource.TaskDataBase
import com.example.taskapp.Tasks.Data.Repository.TaskRepositoryImpl
import com.example.taskapp.Tasks.Domain.Repository.TaskRepository
import com.example.taskapp.Tasks.Domain.UseCase.AddTask
import com.example.taskapp.Tasks.Domain.UseCase.DeleteTaskCase
import com.example.taskapp.Tasks.Domain.UseCase.GetTask
import com.example.taskapp.Tasks.Domain.UseCase.GetTasks
import com.example.taskapp.Tasks.Domain.UseCase.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDataBase {
        return Room.databaseBuilder(
            app,
            TaskDataBase::class.java,
            TaskDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDataBase): TaskRepository{
        return TaskRepositoryImpl(db.TaskDAO)
    }
    @Provides
    @Singleton
    fun provideTaskUSeCases(repository: TaskRepository): TaskUseCases{
        return TaskUseCases(
            getTasks = GetTasks(repository),
            deleteTask = DeleteTaskCase(repository),
            addTask = AddTask(repository),
            getTask = GetTask(repository)
        )
    }
}