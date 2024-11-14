package com.example.studyapp.UserUI
import androidx.compose.animation.core.animateFloatAsState

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign


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
    var hoverState by remember { mutableStateOf(false) }
    val animatedOffset by animateFloatAsState(
        targetValue = if (hoverState) -10f else 0f // Moves up on hover
    )

    Box(
        modifier = Modifier

            .graphicsLayer(
                translationY = animatedOffset // Smooth vertical movement
            )
            .border(2.dp, if (hoverState) Color.Green else Color.Gray)
            .background(color = Color(0xFF7D956D), shape = RoundedCornerShape(16.dp))
            .padding(30.dp)
            .size(width = 300.dp, height = 150.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "TIMETABLE",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
