package com.example.studyapp.UserUI

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.studyapp.Data.database.Course
import com.example.studyapp.R
import com.example.studyapp.viewModel.CourseViewModel
@Composable
fun CourseScreenHeading() {
    Text(
        text = "AVAILABLE COURSES",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        textAlign = TextAlign.Center,
        color = Color(0xFF036928)
    )
}

@Composable
fun CourseData(courses: List<Course>) {
    LazyColumn {
        items(courses) { course ->
            CourseItem(course)
        }
    }
}

@Composable
fun CourseData(courseViewModel: CourseViewModel) {
    val courses by courseViewModel.allCourses.observeAsState(emptyList())

    CourseData(courses = courses)
}

@Composable
fun CourseItem(course: Course) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Gray)
            .background(Color(0xFF7D956D), shape = RoundedCornerShape(10.dp))
            .size(width = 350.dp, height = 70.dp)
            .padding(16.dp)
    ) {
        Column {
            Text(text = course.courseName, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Topics: ${course.noOfTopics}", color = Color.White)

        }
    }
}

@Composable
fun CourseScreen(courseViewModel: CourseViewModel, navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        CourseScreenHeading()
        CourseData(courseViewModel = courseViewModel)
        addButton(navController)
    }
}
@Composable
fun addButton(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Right, // Aligns the button to the far right
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.navigate("add_course_screen") }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add new Course",
                 tint = Color(0xFF036928)
            )
        }
    }
}

@Composable
fun CourseScreenPreview() {
    val sampleCourses = listOf(
        Course(courseID = 1, courseName = "Mathematics", noOfTopics = 10, maximumXp = 200),
        Course(courseID = 2, courseName = "Physics", noOfTopics = 8, maximumXp = 150),
        Course(courseID = 3, courseName = "Chemistry", noOfTopics = 12, maximumXp = 250)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        CourseScreenHeading()
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        CourseData(courses = sampleCourses)
        Spacer(modifier = Modifier.height(280.dp))
        val navController = rememberNavController()

        Footer(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseScreen() {
    CourseScreenPreview()

}
