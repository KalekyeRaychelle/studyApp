package com.example.studyapp.Data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(course: Course)
    @Update
    suspend fun update(course: Course)

    @Query("SELECT * FROM Courses WHERE courseName= :courseName")
    fun getCourseDetails(courseName:String): Flow<Course?>

    @Query("SELECT * FROM Courses")
    fun getAllCourses(): LiveData<List<Course>>

    }
