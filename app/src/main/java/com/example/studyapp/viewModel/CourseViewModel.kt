package com.example.studyapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.studyapp.Data.database.Course
import com.example.studyapp.Data.database.CourseRepository
import com.example.studyapp.Data.database.studyAppdatabase

class CourseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CourseRepository
    val allCourses: LiveData<List<Course>>

    init {
        val database = studyAppdatabase.getDatabase(application)
        val courseDAO = database.courseDAO()
        repository = CourseRepository(courseDAO)
        allCourses = repository.allCourses
    }
}
