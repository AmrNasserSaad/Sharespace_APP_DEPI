package com.example.shareworkspace.presentation.main.confirmation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.R
import com.example.shareworkspace.core.navigation.routes.model.Routes.HOME
import com.example.shareworkspace.presentation.main.home.components.MainTopAppBar
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.TextColor
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmScreen(navController: NavController) {

    // for btn sheet
    val showBottomSheet = remember {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState()
    // for btn sheet

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = 46.dp
            )
    ) {
        MainTopAppBar(title = "") {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Malone Sharespace",
                fontWeight = FontWeight.Bold,
                fontFamily = Montserrat,
                fontSize = 24.sp,
                color = TextColor
            )
            Text(
                text = "Mansoura, jehan",
                fontWeight = FontWeight.Normal,
                fontFamily = Montserrat,
                fontSize = 16.sp,
                color = TextColor
            )
            Spacer(modifier = Modifier.height(20.dp))
            DetailItem(label = "Number of people", value = "4 people")
            Spacer(modifier = Modifier.height(20.dp))
            DetailItem(
                label = "The appointment",
                value = " October 10, 2024    -    8:00 | 10:00 PM"
            )
            Spacer(modifier = Modifier.height(20.dp))
            DetailItem(label = "Time", value = "2 hours")
            Spacer(modifier = Modifier.height(16.dp))
            DetailItem(label = "Money", value = "60 LE ")
        }

        Spacer(modifier = Modifier.weight(0.85f))

        Button(
            onClick = {
                // for btn sheet
                showBottomSheet.value = true

            },
            colors = ButtonDefaults.buttonColors(Primary500),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Confirm",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        if (showBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet.value = false
                },
                sheetState = sheetState
            ) {
                // Sheet content

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_dn),
                        contentDescription = "",
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Done",
                        fontWeight = FontWeight.Bold,
                        fontFamily = Montserrat,
                        fontSize = 24.sp,
                        color = Primary500
                    )

                }
            }
            // Delay for 2 seconds and navigate to the home screen
            LaunchedEffect(Unit) {
                delay(1000L)  // 2 seconds delay
                navController.navigate("MAIN_SCREEN") {
                    popUpTo("Confirm_Screen") {
                        inclusive = true
                    }
                    popUpTo("request_screen") {
                        inclusive = true
                    }
                    popUpTo("Details_Screen") {
                        inclusive = true
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ConfirmScreenPreview() {
    ConfirmScreen(rememberNavController())
}

