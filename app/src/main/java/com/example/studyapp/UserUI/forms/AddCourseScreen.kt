package com.example.studyapp.UserUI.forms

import androidx.compose.foundation.Image
import com.example.studyapp.UserUI.Footer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studyapp.Data.database.Course
import com.example.studyapp.R
import com.example.studyapp.UserUI.CourseHeading
import com.example.studyapp.UserUI.CourseScreenPreview
import com.example.studyapp.viewModel.CourseViewModel
@Composable
fun AddCourseHeading() {
    Text(
        text = "NEW COURSE",
        fontSize = 45.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        textAlign = TextAlign.Center,
        color = Color(0xFF428042)
    )
}

@Composable
fun CourseNameField(courseName: MutableState<String>) {
    TextField(
        value = courseName.value,
        onValueChange = { courseName.value = it },
        label = { Text("Course Name", fontSize = 18.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun NoofUnitsField(noOfUnits: MutableState<String>) {
    TextField(
        value = noOfUnits.value,
        onValueChange = { noOfUnits.value = it },
        label = { Text("Number Of Units:", fontSize = 18.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun AddCourseButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF428042))
    ) {
        Text("ADD COURSE", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AddCourseScreen(navController: NavController) {
    val courseName = remember { mutableStateOf("") }
    val noOfUnits = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AddCourseHeading()

            CourseNameField(courseName)
            NoofUnitsField(noOfUnits)

            AddCourseButton {

                navController.popBackStack() // Navigates back to the previous screen
            }


            Button(
                onClick = { navController.popBackStack() }, // Go back to previous screen
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddCourseScreen() {
    val courseName = remember { mutableStateOf("") }
    val noOfUnits = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AddCourseHeading()
            CourseNameField(courseName)
            NoofUnitsField(noOfUnits)
            AddCourseButton {

            }
        }
    }
}
