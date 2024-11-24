package com.example.studyapp.Data.database
import com.example.studyapp.Data.database.Timetable
import com.example.studyapp.Data.database.TimetableWithCourse
import com.example.studyapp.Data.database.TimeTableDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class TimetableRepository(private val timetableDAO: TimeTableDAO) {

    suspend fun getAllSubjectsForDay(day: String, username: String): List<Timetable> {
        return withContext(Dispatchers.IO) {
            timetableDAO.getAllSubjectsForDay(day, username)
        }
    }

    suspend fun insertTimetableEntry(timetable: Timetable) {
        withContext(Dispatchers.IO) {
            timetableDAO.insertTimetableEntry(timetable)
        }
    }

    suspend fun updateTimeForCourse(
        day: String, courseName: String, newStartTime: String, newEndTime: String, username: String
    ) {
        withContext(Dispatchers.IO) {
            timetableDAO.updateTimeForCourse(day, courseName, newStartTime, newEndTime, username)
        }
    }

    suspend fun updateCourseForDay(
        day: String, oldCourseName: String, newCourseName: String, username: String
    ) {
        withContext(Dispatchers.IO) {
            timetableDAO.updateCourseForDay(day, oldCourseName, newCourseName, username)
        }
    }
}
