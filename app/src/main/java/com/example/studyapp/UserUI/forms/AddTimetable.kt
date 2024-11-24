package com.example.studyapp.UserUI.forms


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studyapp.Data.database.TimeTableDAO

import com.example.studyapp.Data.database.Timetable
import com.example.studyapp.Data.database.TimetableRepository
import com.example.studyapp.Data.database.User
import com.example.studyapp.viewModel.TimetableViewModel
import com.example.studyapp.viewModel.UserViewModel
import kotlinx.coroutines.launch


@Composable
fun AddSubjectHeading(){
    Text(
        text = "NEW COURSE",
        fontSize = 45.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        textAlign = TextAlign.Center,
        color = Color(0xFF428042)
    )
}
@Composable
fun AddCourseNameField(courseName: MutableState<String>) {
    TextField(
        value = courseName.value,
        onValueChange = { courseName.value = it },
        label = { Text("Course Name", fontSize = 18.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    )
}
@Composable
fun AddStartTimeField(startTime: MutableState<String>) {
    TextField(
        value = startTime.value,
        onValueChange = { startTime.value = it },
        label = { Text("Start Time", fontSize = 18.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun AddEndTimeField(endTime: MutableState<String>) {
    TextField(
        value = endTime.value,
        onValueChange = { endTime.value = it },
        label = { Text("End Time", fontSize = 18.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    )
}


@Composable
fun DayDropdownField(selectedDay: MutableState<String>, daysOfWeek: List<String>) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        TextField(
            value = selectedDay.value,
            onValueChange = {}, // The dropdown handles selection
            label = { Text("Select Day", fontSize = 18.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable { expanded = true },
            shape = RoundedCornerShape(8.dp),
            readOnly = true, // Makes it uneditable by typing
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon"
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            daysOfWeek.forEach { day ->
                DropdownMenuItem(
                    text = { Text("$day") },
                    onClick = {  selectedDay.value = day
                        expanded = false },)

            }
        }
    }
}
@Composable
fun AddTimetableScreen(viewModel: TimetableViewModel, userViewModel: UserViewModel,navController: NavHostController) {
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val selectedDay = remember { mutableStateOf("") }
    val courseName = remember { mutableStateOf("") }
    val startTime = remember { mutableStateOf("") } // Add this line
    val endTime = remember { mutableStateOf("") } // Add this line
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val currentUser = userViewModel.currentUser.observeAsState().value

    // Ensure the username is available
    val username = currentUser?.username

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {
                // Navigate back to the timetable screen
                navController.popBackStack()
            },
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        AddSubjectHeading()
        DayDropdownField(selectedDay = selectedDay, daysOfWeek = daysOfWeek)
        AddCourseNameField(courseName = courseName)
        AddStartTimeField(startTime = startTime) // Add the start time field
        AddEndTimeField(endTime = endTime) // Add the end time field

        Button(
            onClick = {
                coroutineScope.launch {
                    // Check if the selected day, course name, and username are provided
                    if (selectedDay.value.isNotEmpty() && courseName.value.isNotEmpty() && username != null
                        && startTime.value.isNotEmpty() && endTime.value.isNotEmpty()) {
                        try {
                            // Create a new timetable entry
                            val timetable = Timetable(
                                day = selectedDay.value,
                                courseName = courseName.value,
                                username = username,
                                startTime = startTime.value,
                                endTime = endTime.value
                            )

                            // Call the ViewModel's addTimetable function to insert the timetable
                            viewModel.addTimetable(timetable)

                            // Show a success message
                            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show()

                            // Clear the form fields
                            selectedDay.value = ""
                            courseName.value = ""
                            startTime.value = ""
                            endTime.value = ""
                        } catch (e: Exception) {
                            // Show an error message in case of failure
                            Toast.makeText(context, "Failed to Add: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Show an error if the form fields are not completed
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Save")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewAddTimetableScreen() {
    // Mock TimetableDAO implementation
    val mockTTDAO = object : TimeTableDAO {
        override suspend fun insertTimetableEntry(timetable: Timetable) {
            // Simulate insert functionality
        }
        override suspend fun getAllSubjectsForDay(day: String, username: String): List<Timetable> {
            // Simulate getting all subjects for a day
            return emptyList()
        }
        override suspend fun updateTimeForCourse(
            day: String,
            courseName: String,
            newStartTime: String,
            newEndTime: String,
            username: String
        ) {
            // Simulate updating time for a course
        }

        override suspend fun updateCourseForDay(
            day: String,
            oldCourseName: String,
            newCourseName: String,
            username: String
        ) {
            // Simulate updating course for a day
        }
    }

    // Mock TimetableRepository
    val mockTTRepository = TimetableRepository(mockTTDAO)

    // Mock UserViewModel
    val mockUserViewModel = UserViewModel()
    mockUserViewModel.setUser(User(username = "test_user", password = "password123"))

    // Mock TimetableViewModel
    val mockTimetableViewModel = TimetableViewModel(mockTTRepository, mockUserViewModel)

    // Set up the navigation
    val navController = rememberNavController()

    // Set up the NavHost
    NavHost(navController = navController, startDestination = "add_timetablescreen") {
        composable("add_timetablescreen") {
            AddTimetableScreen(viewModel = mockTimetableViewModel, userViewModel = mockUserViewModel, navController = navController)
        }
    }
}
