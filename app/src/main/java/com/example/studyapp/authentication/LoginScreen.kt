package com.example.studyapp.authentication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyapp.R
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyapp.Data.UserRepository
import com.example.studyapp.Data.database.User
import com.example.studyapp.Data.database.UserDAO
import com.example.studyapp.viewmodel.LoginViewModel
import com.example.studyapp.viewmodel.SignUpViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


@Composable
fun LoginHeading() {
    Text(
        text = "LOG IN",
        fontSize = 45.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        textAlign = TextAlign.Center
    )
}
@Composable
fun UsernameField(username: String, onUsernameChange: (String) -> Unit) {
    TextField(
        value = username,
        onValueChange = onUsernameChange,
        label = { Text("Username", fontSize = 18.sp) }, // Adjust font size
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .clip(RoundedCornerShape(8.dp)), // Rounded corners
        shape = RoundedCornerShape(8.dp),

    )
}

@Composable
fun PasswordField(password: String, onPasswordChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password", fontSize = 18.sp) }, // Adjust font size
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(8.dp)), // Rounded corners
        shape = RoundedCornerShape(8.dp)

    )
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF428042) )
    ) {
        Text("LOGIN", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SignupRedirect(onClick: () -> Unit) {
    Text(
        text = "Don't have an account? Signup",
        color = Color(0xFF6200EE), // Set your color
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick = onClick),
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}


@Composable
fun LoginScreen(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                LoginHeading()
                UsernameField(username = username, onUsernameChange = { username = it })
                PasswordField(password = password, onPasswordChange = { password = it })
                LoginButton(onClick = {
                    if (username.isNotBlank() && password.isNotBlank()) {
                        viewModel.login(username, password,
                            onLoginSuccess = {

                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            },
                            onFailure = {

                                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
                            }
                        )
                    } else {
                        Toast.makeText(context, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                    }
                })
                SignupRedirect(onClick = { /* Redirect to signup screen */ })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Simulating a mock viewModel and flexible login flow
    val mockUserDAO = object : UserDAO {
        override suspend fun insert(user: User) {
            // Simulate insert logic
        }

        override suspend fun update(user: User) {
            // Simulate update logic
        }

        override suspend fun delete(user: User) {
            // Simulate delete logic
        }

        override fun getUser(username: String, password: String): Flow<User> {
            return flowOf(User(username = username, password = password))
        }

    }
    val mockUserRepository = UserRepository(mockUserDAO)


    val mockViewModel = object : LoginViewModel(mockUserRepository) {
        override fun login(username: String, password: String, onLoginSuccess: () -> Unit, onFailure: () -> Unit) {
            // Dynamic validation based on username/password
            if (username == "testUser" && password == "testPass") {
                onLoginSuccess() // Simulate successful login
            } else {
                onFailure() // Simulate failure for other cases
            }

        }
    }

    // You can preview with different values to simulate different login scenarios
    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        viewModel = mockViewModel
    )
}