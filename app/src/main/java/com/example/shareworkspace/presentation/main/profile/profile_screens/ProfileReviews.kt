package com.example.shareworkspace.presentation.main.profile.profile_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.presentation.main.home.components.MainTopAppBar
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor

@Composable
fun ProfileReviews(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        MainTopAppBar(title = "My Reviews") {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Screen content
        MyReviewCard()
        MyReviewCard()
        MyReviewCard()
        MyReviewCard()
    }
}

@Composable
fun MyReviewCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Error Workspace",
                    fontSize = 14.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = TextColor
                )
                Spacer(modifier = Modifier.weight(1f))

                // Row for Rating Stars
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Filled Star",
                        tint = Color.Gray, // Gold star
                        modifier = Modifier.size(18.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Filled Star",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(20.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Filled Star",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(20.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Filled Star",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(20.dp)
                    )
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Empty Star",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(20.dp)
                    )
                }

            }

            Spacer(modifier = Modifier.height(12.dp))

            // Row for Avatar and User Name


            Spacer(modifier = Modifier.height(12.dp))

            // Review Title


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Everything's fine",
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    color = TextFieldColor
                )
                Text(
                    text = "October 10, 2023",
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                    color = TextColor
                )

            }


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileReviewsPreview() {
    ProfileReviews(rememberNavController())
}