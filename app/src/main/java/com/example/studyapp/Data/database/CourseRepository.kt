package com.example.studyapp.Data.database
import androidx.lifecycle.LiveData
import  com.example.studyapp.Data.database.Course
import  com.example.studyapp.Data.database.CourseDAO
import kotlinx.coroutines.flow.Flow

class CourseRepository (private val courseDAO: CourseDAO){
    val allCourses: LiveData<List<Course>> = courseDAO.getAllCourses()
    fun getCourseDetailsStream(courseName:String): Flow<Course?> {
        return courseDAO.getCourseDetails(courseName)
    }
    suspend fun insertCourse(course: Course) {
        courseDAO.insert(course)
    }

    suspend fun updateCourse(course: Course) {
        courseDAO.update(course)
    }
}