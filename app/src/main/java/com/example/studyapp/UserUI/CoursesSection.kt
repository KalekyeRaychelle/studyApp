package com.example.studyapp.UserUI
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable

fun CourseHeading() {
    Text(
        text = "AVAILABLE COURSES",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        textAlign = TextAlign.Left,
         color = Color(0xFF036928)
    )
}

@Composable
fun CourseCard(courseTitle: String) {
    Box(modifier = Modifier
        .border(1.dp, Color.Gray)
        .padding(8.dp)
        .background(color = Color(0xFF4E4E4E).copy(alpha = 0.8f), shape = RoundedCornerShape(16.dp))
        .size(width = 100.dp, height = 100.dp)
    ) {
        Text(text = courseTitle,  color = Color.White,textAlign = TextAlign.Center,)

    }
}

@Composable
fun AvailableCoursesSection() {
    Column(modifier = Modifier
        .border(1.dp, Color.Gray)
        .background(Color(0xFF7D956D), shape = RoundedCornerShape(16.dp))
        .padding(30.dp)
        .size(width = 300.dp, height = 150.dp)) {

        Row {
            CourseCard(courseTitle = "MOBILE APP")
            Spacer(modifier = Modifier.width(8.dp))
            CourseCard(courseTitle = "automata")
        }
    }
}
