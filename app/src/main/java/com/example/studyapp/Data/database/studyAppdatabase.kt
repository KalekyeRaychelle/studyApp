package com.example.studyapp.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [User::class,Course::class,Timetable::class], version = 3, exportSchema = false)
abstract class studyAppdatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun courseDAO(): CourseDAO
    abstract fun timetableDAO():TimeTableDAO

    companion object {
        @Volatile
        private var Instance: studyAppdatabase? = null

        fun getDatabase(context: Context): studyAppdatabase {

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, studyAppdatabase::class.java, "studyApp_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}