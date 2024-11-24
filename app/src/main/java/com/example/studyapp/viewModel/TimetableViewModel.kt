package com.example.studyapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyapp.Data.database.Timetable
import com.example.studyapp.Data.database.TimetableRepository
import com.example.studyapp.Data.database.TimetableState
import com.example.studyapp.Data.database.TimetableWithCourse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class TimetableViewModel(private val timetableRepository: TimetableRepository, private val userViewModel: UserViewModel) : ViewModel() {

    private val _timetableState = MutableStateFlow(TimetableState())
    val timetableState: StateFlow<TimetableState> = _timetableState

    init {
        // Observe the current user to load the timetable when a user is logged in
        userViewModel.currentUser.observeForever { user ->
            user?.let {
                loadTimetable(it.username)  // Fetch timetable based on username
            }
        }
    }

    private fun loadTimetable(username: String) {
        // Use the repository to fetch the timetable data for the logged-in user
        viewModelScope.launch {
            _timetableState.value = TimetableState(isLoading = true)
            try {
                // Fetch timetable from repository for a specific user
                val timetableData = timetableRepository.getAllSubjectsForDay("Monday", username) // Example: Fetch for Monday, adjust as necessary
                _timetableState.value = TimetableState(timetables = timetableData, isLoading = false, error = null)
            } catch (e: Exception) {
                _timetableState.value = TimetableState(isLoading = false, error = e.message)
            }
        }
    }

    suspend fun addTimetable(timetable: Timetable) {
        // Insert timetable entry through the repository
        timetableRepository.insertTimetableEntry(timetable)
    }
}
