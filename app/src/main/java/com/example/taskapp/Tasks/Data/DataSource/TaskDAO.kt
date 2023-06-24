package com.example.taskapp.Tasks.Data.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow
import androidx.room.Query
import com.example.taskapp.Tasks.Domain.Model.Task

@Dao
interface TaskDAO {

    @Query("SELECT * FROM Task")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(Task: Task)

    @Delete
    suspend fun deleteTask(Task: Task)
}