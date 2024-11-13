package com.example.studyapp.UserUI

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun THeading(){

        Text(
            text = "TIMETABLE",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            textAlign = TextAlign.Left,
            color = Color(0xFF036928)
        )


}
@Composable
fun TimetableSection() {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Gray)
            .background(color = Color(0xFFD1495B), shape = RoundedCornerShape(16.dp))
            .padding(30.dp)
            .size(width = 300.dp, height = 150.dp)
    ) {

        Text(text = "TIMETABLE")

    }
}
