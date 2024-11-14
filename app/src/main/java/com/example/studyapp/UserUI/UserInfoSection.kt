package com.example.studyapp.UserUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserInfoSection() {
    Box(
        modifier = Modifier
            .background(color=Color(0xFF7D956D))
            .padding(16.dp)
    ) {
        Row {
            Text(
                text = "WELCOME!",
                color = Color.White,
                fontSize = 20.sp, // Increase font size
                fontWeight = FontWeight.Bold // Make it bold if desired
            )
            Spacer(modifier = Modifier.weight(1f)) // Push "XP" to the far right
            Text(
                text = "XP",
                color = Color.White,
                fontSize = 20.sp, // Increase font size for XP as well
                fontWeight = FontWeight.Bold
            )
        }
    }
}
