package com.example.studyapp

import  com.example.studyapp.UserUI.HomeScreen
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
import com.example.studyapp.Data.database.CourseRepository
import com.example.studyapp.Data.database.TimetableRepository
import com.example.studyapp.Data.database.User
import com.example.studyapp.Data.database.UserDAO
import com.example.studyapp.Data.database.studyAppdatabase
import com.example.studyapp.UserUI.CourseScreen
import com.example.studyapp.UserUI.TimetableScreen
import com.example.studyapp.UserUI.forms.AddCourseScreen
import com.example.studyapp.UserUI.forms.AddTimetableScreen
import com.example.studyapp.viewModel.CourseViewModel
import com.example.studyapp.viewModel.CourseViewModelFactory
import com.example.studyapp.viewModel.SignUpViewModel
import com.example.studyapp.viewModel.LoginViewModel
import com.example.studyapp.viewModel.LoginViewModelFactory
import com.example.studyapp.viewModel.TimetableViewModel
import com.example.studyapp.viewModel.TimetableViewModelFactory
import com.example.studyapp.viewModel.UserViewModel
import com.example.studyapp.viewModel.UserViewModelFactory

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    val navController = rememberNavController()
                    val userViewModel: UserViewModel = viewModel()

                    // Assuming you are setting the current user after login
                    val user = User(username = "student1", password = "password")
                    userViewModel.setUser(user)

                    Navigation(navController = navController, userViewModel = userViewModel)

                }
            }
        }
    }
    @Composable
    fun Navigation(navController: NavHostController, userViewModel: UserViewModel) {
        val viewModel: SignUpViewModel by viewModels {
            SignUpViewModelFactory(UserRepository(studyAppdatabase.getDatabase(this).userDAO()))
        }

        val loginViewModel: LoginViewModel by viewModels {
            LoginViewModelFactory(UserRepository(studyAppdatabase.getDatabase(this).userDAO()))
        }

        val courseViewModel: CourseViewModel by viewModels(){
            CourseViewModelFactory(CourseRepository(studyAppdatabase.getDatabase(this).courseDAO()))
        }
        val timetableViewModel: TimetableViewModel by viewModels {
            TimetableViewModelFactory(TimetableRepository(studyAppdatabase.getDatabase(this).timetableDAO()), userViewModel)
        }
        val userViewModel:UserViewModel by viewModels(){
            UserViewModelFactory(UserRepository(studyAppdatabase.getDatabase(this).userDAO()))
        }
        NavHost(navController = navController, startDestination = "login_screen") {
            composable("login_screen") {
                LoginScreen(modifier = Modifier, viewModel = loginViewModel,navController = navController)
            }
            composable("signup_screen") {
                SignupScreen(viewModel = viewModel,navController = navController)
            }
            composable("add_course_screen") {
                AddCourseScreen(navController = navController, viewModel = courseViewModel)
            }
            composable("home_screen") {
                HomeScreen(navController = navController)
            }
            composable("course_screen") {
                CourseScreen(courseViewModel = courseViewModel, navController = navController)
            }
            composable("timetable_screen") {
                TimetableScreen(timetableViewModel = timetableViewModel, navController = navController)
            }
            composable("add_timetablescreen"){
                AddTimetableScreen( viewModel = timetableViewModel, userViewModel = userViewModel, navController = navController)
            }
        }
    }


}