package com.example.studyapp.authentication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyapp.R
import com.example.studyapp.ui.theme.StudyAppTheme
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale


class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignupScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SignupHeading() {
    Text(
        text = "SIGN UP",
        fontSize = 45.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        textAlign = TextAlign.Center
    )
}
@Composable
fun SignupUsernameField(username: String, onUsernameChange: (String) -> Unit) {
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
fun SignupPasswordField(password: String, onPasswordChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password", fontSize = 18.sp) },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)

    )
}

@Composable
fun SignupButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF428042) )
    ) {
        Text("SIGN UP", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun LoginRedirect(onClick: () -> Unit) {
    Text(
        text = "Already have an account? Log In" ,
        color = Color(0xFF6200EE), // Set your color
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick = onClick),
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun SignupScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                contentScale = ContentScale.Crop // Make background cover entire screen
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                SignupHeading()
                SignupUsernameField(username = username, onUsernameChange = { username = it })
                SignupPasswordField(password = password, onPasswordChange = { password = it })
                SignupButton(onClick = {})
                LoginRedirect(onClick = { /* Redirect to signup screen */ })
            }
        }
    }
}