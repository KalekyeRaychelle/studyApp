package com.example.studyapp.Data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Courses")
data class Course (
    @PrimaryKey(autoGenerate = true)
    val courseID: Int,
    val courseName: String,
    val noOfTopics:Int,
    val maximumXp:Int
){}