package com.example.studyapp.UserUI
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyapp.R
import com.example.studyapp.authentication.LoginScreen

import com.example.studyapp.ui.theme.StudyAppTheme

class footer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyAppTheme {


            }
        }
    }
}



@Composable
fun Footer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF7D956D))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle home click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home"

                )
            }

            IconButton(onClick = { /* Handle current courses click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.course),
                    contentDescription = "Current Courses"
                )
            }

            IconButton(onClick = { /* Handle discussions click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.meeting),
                    contentDescription = "Current Discussions"
                )
            }

            IconButton(onClick = { /* Handle profile click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "User Profile"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FooterPreview() {
    MaterialTheme {
        Surface {
            Footer()
        }
    }
}



