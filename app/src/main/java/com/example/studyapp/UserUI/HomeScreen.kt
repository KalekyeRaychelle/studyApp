import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studyapp.UserUI.AvailableCoursesSection
import com.example.studyapp.UserUI.CourseHeading
import com.example.studyapp.UserUI.Footer
import com.example.studyapp.UserUI.THeading
import com.example.studyapp.UserUI.TimetableSection
import com.example.studyapp.UserUI.UserInfoSection
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color(0xFFEDD9A8))
    ) {
        UserInfoSection()
        Spacer(modifier = Modifier.height(16.dp))
        THeading()
        Spacer(modifier = Modifier.height(4.dp))
        TimetableSection()
        Spacer(modifier = Modifier.height(16.dp))
        CourseHeading()
        Spacer(modifier = Modifier.height(16.dp))
        AvailableCoursesSection()



        Spacer(modifier = Modifier.height(150.dp))
        Footer(navController = navController)
    }
}
@Composable
@Preview
fun HomeScreenPreview(){
    val navController = rememberNavController()

    HomeScreen(navController = navController)
}
