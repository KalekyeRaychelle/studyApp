package com.example.studyapp

import HomeScreen
import com.example.studyapp.authentication.LoginScreen
import com.example.studyapp.authentication.PreviewSignupScreen
import com.example.studyapp.authentication.SignupScreen
import com.example.studyapp.ui.theme.StudyAppTheme
import com.example.studyapp.UserUI.PreviewCourseScreen
import com.example.studyapp.viewModel.SignUpViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studyapp.Data.UserRepository
import com.example.studyapp.Data.database.UserDAO
import com.example.studyapp.Data.database.studyAppdatabase
import com.example.studyapp.UserUI.CourseScreen
import com.example.studyapp.UserUI.forms.AddCourseScreen
import com.example.studyapp.viewModel.CourseViewModel
import com.example.studyapp.viewModel.SignUpViewModel
import com.example.studyapp.viewModel.LoginViewModel
import com.example.studyapp.viewModel.LoginViewModelFactory
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyAppTheme {

                Surface(modifier = androidx.compose.ui.Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController() // Create NavController

                    Navigation(navController = navController)
                }
            }
        }
    }
    @Composable
    fun Navigation(navController: NavHostController) {
        val viewModel: SignUpViewModel by viewModels {
            SignUpViewModelFactory(UserRepository(studyAppdatabase.getDatabase(this).userDAO()))
        }

        val loginViewModel: LoginViewModel by viewModels {
            LoginViewModelFactory(UserRepository(studyAppdatabase.getDatabase(this).userDAO()))
        }
        val courseViewModel: CourseViewModel by viewModels()
        NavHost(navController = navController, startDestination = "login_screen") {
            composable("login_screen") {
                LoginScreen(modifier = Modifier, viewModel = loginViewModel,navController = navController) // Pass navController to LoginScreen
            }
            composable("signup_screen") {
                SignupScreen(viewModel = viewModel,navController = navController) // Pass navController to SignUpScreen
            }
            composable("add_course_screen") {
                AddCourseScreen(navController = navController) // Added AddCourseScreen
            }
            composable("home_screen") {
                HomeScreen(navController = navController) // Pass navController to HomeScreen
            }
            composable("course_screen") {
                CourseScreen(courseViewModel = courseViewModel, navController = navController) // Pass navController to CourseScreen
            }
        }
    }


}