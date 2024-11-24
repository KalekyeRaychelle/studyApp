package com.example.studyapp.Data.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(
    tableName = "timetable",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["username"],
            childColumns = ["username"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["username"]),
        Index(value = ["day", "startTime", "endTime", "username"], unique = true)
    ]
)
data class Timetable(
    @PrimaryKey(autoGenerate = true) val tableId: Int=0,
    val username: String,
    val courseName: String,
    val day: String,
    val startTime: String,
    val endTime: String
)
