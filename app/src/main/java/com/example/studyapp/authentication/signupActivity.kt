package com.example.studyapp.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.studyapp.ui.theme.StudyAppTheme
import com.example.studyapp.Data.UserRepository
import com.example.studyapp.Data.database.studyAppdatabase
import com.example.studyapp.viewModel.SignUpViewModelFactory
import com.example.studyapp.viewmodel.SignUpViewModel

class signupActivity : ComponentActivity() {
    private val viewModel: SignUpViewModel by viewModels {
        SignUpViewModelFactory(
            UserRepository(studyAppdatabase.getDatabase(this).userDAO())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyAppTheme {
                SignupScreen(viewModel = viewModel)
            }
        }
    }
}
