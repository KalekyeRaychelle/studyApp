package com.example.studyapp.UserUI
import androidx.compose.animation.core.animateFloatAsState

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Card
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign


@Composable
fun THeading() {
    Text(
        text = "TIMETABLE",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        textAlign = TextAlign.Start,
        color = Color(0xFF036928) // Green color
    )
}

@Composable
fun TimetableSection() {
    var hoverState by remember { mutableStateOf(false) }
    val animatedOffset by animateFloatAsState(
        targetValue = if (hoverState) -10f else 0f // Moves up on hover
    )

    Card(
        modifier = Modifier
            .height(45.dp)
            .padding(16.dp)
            .graphicsLayer(translationY = animatedOffset)
            .background(color = Color(0xFF7D959D))
            .shadow(8.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.height(40.dp),
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
}