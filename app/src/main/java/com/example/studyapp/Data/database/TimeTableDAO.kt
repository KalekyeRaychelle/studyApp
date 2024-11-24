package com.example.studyapp.Data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface TimeTableDAO {

    @Insert
    suspend fun insertTimetableEntry(timetable: Timetable)

    @Query("""
    SELECT * 
    FROM timetable
    WHERE day = :day AND username = :username
    """)
    suspend fun getAllSubjectsForDay(day: String, username: String): List<Timetable>

    @Query("""
    UPDATE timetable 
    SET startTime = :newStartTime, endTime = :newEndTime
    WHERE day = :day AND courseName = :courseName AND username = :username
    """)
    suspend fun updateTimeForCourse(
        day: String,
        courseName: String,
        newStartTime: String,
        newEndTime: String,
        username: String
    )

    @Query("""
    UPDATE timetable 
    SET courseName = :newCourseName 
    WHERE day = :day AND courseName = :oldCourseName AND username = :username
    """)
    suspend fun updateCourseForDay(
        day: String,
        oldCourseName: String,
        newCourseName: String,
        username: String
    )
}
