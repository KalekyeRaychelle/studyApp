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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
fun Footer(navController: NavController ,  modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF7D959D))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Navigate to Home Screen
            IconButton(onClick = { navController.navigate("home_screen") }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home"
                )
            }

            // Navigate to Current Courses Screen
            IconButton(onClick = { navController.navigate("course_screen") }) {
                Icon(
                    painter = painterResource(id = R.drawable.course),
                    contentDescription = "Current Courses"
                )
            }

            // Navigate to Current Discussions Screen (You can implement a screen for this)
            IconButton(onClick = { navController.navigate("discussions_screen") }) {
                Icon(
                    painter = painterResource(id = R.drawable.meeting),
                    contentDescription = "Current Discussions"
                )
            }

            // Navigate to User Profile Screen (You can implement a profile screen)
            IconButton(onClick = { navController.navigate("profile_screen") }) {
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
            val navController = rememberNavController()
            Footer(navController = navController)
        }
    }
}



