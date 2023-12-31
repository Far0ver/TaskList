package com.example.taskapp.Tasks.Domain.Model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "Title") var Title: String,
    @ColumnInfo(name = "Description") val Desc: String,
    @ColumnInfo(name ="Time") val Time: String,
    @ColumnInfo(name ="Favorites") var Favorites: Boolean = false,
)
class InvalidTaskException(message: String): Exception(message)
