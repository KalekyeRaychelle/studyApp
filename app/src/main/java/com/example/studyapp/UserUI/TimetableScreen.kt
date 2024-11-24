package com.example.studyapp.UserUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyapp.viewModel.TimetableViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studyapp.Data.database.TimeTableDAO
import com.example.studyapp.Data.database.Timetable
import com.example.studyapp.Data.database.TimetableRepository
import com.example.studyapp.Data.database.TimetableWithCourse
import com.example.studyapp.R
import com.example.studyapp.viewModel.UserViewModel


@Composable
fun TimetableHeading(){
    Text(
        text = "TIMETABLE",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        textAlign = TextAlign.Start,
        color = Color(0xFF036928)
    )
}
@Composable
fun ActualHeading(){
    Text(
        text = "MY DAY",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        textAlign = TextAlign.Start,
        color = Color(0xFF036928)

    )}
@Composable
fun TimetableScreen(
    timetableViewModel: TimetableViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val timetableState = timetableViewModel.timetableState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        if (timetableState.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (timetableState.value.error != null) {
            Text(
                text = "Error: ${timetableState.value.error}",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                TimetableHeading()

                val groupedByDay = timetableState.value.timetables.groupBy { it.day }

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0x330000FF))
                        .padding(8.dp)
                ) {
                    groupedByDay.forEach { (day, subjects) ->
                        item {
                            Text(
                                text = day,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            )
                        }
                        items(subjects) { timetable ->
                            TimetableItem(
                                timetable = timetable,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                                    .padding(16.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            Button(onClick = { navController.navigate("add_timetablescreen") }) {

            }

            Footer(
                navController = navController,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}


@Composable
fun TimetableItem(timetable: Timetable, modifier: Modifier) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "${timetable.courseName}",

        )
        Text(
            text = "Time: ${timetable.startTime} - ${timetable.endTime}",

        )
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewTimetableScreen() {
    val mockTTDAO = object : TimeTableDAO {
        override suspend fun insertTimetableEntry(timetable: Timetable) {
            TODO("Not yet implemented")
        }
        override suspend fun getAllSubjectsForDay(day: String, username: String): List<Timetable> {
            return listOf(
                Timetable(1, "username1", "Math", "Monday", "08:00", "10:00"),
                Timetable(2, "username1", "Science", "Monday", "10:00", "12:00")
            )
        }




        override suspend fun updateTimeForCourse(
            day: String,
            courseName: String,
            newStartTime: String,
            newEndTime: String,
            username: String
        ) {
            TODO("Not yet implemented")
        }

        override suspend fun updateCourseForDay(
            day: String,
            oldCourseName: String,
            newCourseName: String,
            username: String
        ) {
            TODO("Not yet implemented")
        }

    }

    val mockTTRepository = TimetableRepository(mockTTDAO)
    val mockUserViewModel=UserViewModel()
    val mockViewModel = TimetableViewModel(mockTTRepository,mockUserViewModel)

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "timetable_screen") {
        composable("timetable_screen") {
            TimetableScreen(
                timetableViewModel = mockViewModel,
                navController = navController
            )
        }
    }

}