package com.example.studyapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyapp.Data.UserRepository
import com.example.studyapp.Data.database.Course
import com.example.studyapp.Data.database.CourseRepository
import com.example.studyapp.Data.database.studyAppdatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
class CourseViewModel(private val courseRepository: CourseRepository) : ViewModel() {

    val allCourses: LiveData<List<Course>> = courseRepository.allCourses

    fun getCourseDetailsStream(courseName: String): Flow<Course?> {
        return courseRepository.getCourseDetailsStream(courseName)
    }
    fun addCourse(course: Course, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {

                courseRepository.insertCourse(course)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}


