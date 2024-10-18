package com.example.shareworkspace.presentation.main.profile.profile_screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.example.shareworkspace.presentation.main.home.components.MainTopAppBar
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldBorderColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor

@Composable
fun ProfileDetails(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    val userData by authViewModel.userData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        MainTopAppBar(title = "My Details") {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(16.dp))

        userData?.let { user ->

            val nameParts = user.name.split(" ")
            val firstName = nameParts.getOrNull(0) ?: "N/A"
            val lastName = nameParts.getOrNull(1) ?: "N/A"

            InformationCard("First Name", firstName)
            InformationCard("Last Name", lastName)
            InformationCard("Phone", user.phone)
            InformationCard("National Id", user.nationalId)
           // InformationCard("Location", user.location)
            InformationCard("Location", "Egypt")
        } ?: run {
            // Show a loading indicator or message if user data is not yet available
            Text(text = "Loading user data...", modifier = Modifier.padding(16.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileDetailsPreview() {
    ProfileDetails(rememberNavController())
}

@Composable
fun InformationCard(title: String, information: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = TextFieldBorderColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {

            Text(
                text = title,
                color = TextColor,
                fontFamily = Montserrat,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal

            )
            Text(
                text = information,
                color = TextFieldColor,
                fontFamily = Montserrat,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal

            )

        }
    }


}