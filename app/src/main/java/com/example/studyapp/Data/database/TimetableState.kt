package com.example.studyapp.Data.database

data class TimetableState(
    val isLoading: Boolean = true,
    val timetables: List<Timetable> = emptyList(),
    val error: String? = null
)
