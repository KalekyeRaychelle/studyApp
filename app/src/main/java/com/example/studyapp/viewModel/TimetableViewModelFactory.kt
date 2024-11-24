package com.example.studyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studyapp.Data.database.TimetableRepository
class TimetableViewModelFactory(
    private val timetableRepository: TimetableRepository,
    private val userViewModel: UserViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TimetableViewModel::class.java) -> {
                TimetableViewModel(timetableRepository, userViewModel) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
