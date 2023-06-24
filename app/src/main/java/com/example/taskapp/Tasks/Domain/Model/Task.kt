package com.example.taskapp.Tasks.Domain.Model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "Title") val Title: String,
    @ColumnInfo(name = "Description") val Desc: String,
    @ColumnInfo(name ="Time") val Time: Long
)
class InvalidTaskException(message: String): Exception(message)
