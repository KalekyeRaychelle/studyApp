package com.example.studyapp.UserUI
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.CardElevation
import androidx.compose.ui.draw.shadow

@Composable
fun CourseHeading() {
    Text(
        text = "AVAILABLE COURSES",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        textAlign = TextAlign.Start,
        color = Color(0xFF036928)
    )
}
@Composable
fun CourseCard(courseTitle: String) {
    Card(
        modifier = Modifier

            .fillMaxWidth(0.45f)  // Adjust the width to fit two cards next to each other
            .padding(8.dp)
            .background(color = Color(0xFF7D956D)),
        shape = RoundedCornerShape(12.dp),

    ) {
        Box(
            modifier = Modifier
                .height(40.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = courseTitle,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
@Composable
fun AvailableCoursesSection() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .height(250.dp)
            .background(Color(0xFF7D959D), shape = RoundedCornerShape(16.dp))
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .width(250.dp)

    ) {

        CourseCard(courseTitle = "MOBILE APP")
        Spacer(modifier = Modifier.height(8.dp)) // Space between the cards
        CourseCard(courseTitle = "Automata")
    }
}

