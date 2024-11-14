package com.example.studyapp.UserUI



import com.example.studyapp.authentication.LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.Data.UserRepository
import com.example.studyapp.Data.database.studyAppdatabase

import com.example.studyapp.ui.theme.StudyAppTheme
import com.example.studyapp.viewModel.CourseViewModel


class CourseScreenActivity : ComponentActivity() {
    private val courseViewModel: CourseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyAppTheme {
                Scaffold { paddingValues ->
                    CourseScreen(courseViewModel, Modifier.padding(paddingValues))
                }
            }
        }
    }
}
