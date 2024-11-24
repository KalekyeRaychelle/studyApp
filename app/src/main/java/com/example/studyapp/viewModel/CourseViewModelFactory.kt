package com.example.studyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studyapp.Data.UserRepository
import com.example.studyapp.Data.database.CourseRepository

class CourseViewModelFactory(private val courseRepository: CourseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CourseViewModel(courseRepository) as T
    }
}
