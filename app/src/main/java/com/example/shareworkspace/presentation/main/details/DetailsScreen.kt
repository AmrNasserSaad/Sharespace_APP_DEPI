package com.example.shareworkspace.presentation.main.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.R
import com.example.shareworkspace.presentation.main.home.components.MainTopAppBar
import com.example.shareworkspace.presentation.main.search.CategoryChip
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsScreen(navController: NavController) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontFamily = Montserrat,
                                color = Primary500, fontSize = 18.sp
                            )
                        ) {
                            append("90LE")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Medium,
                                color = TextFieldColor,
                                fontFamily = Montserrat
                            )
                        ) {
                            append(" /Hour")
                        }

                    }
                )
                Card(
                    onClick = { navController.navigate("request_screen") },
                    modifier = Modifier
                        .width(132.dp)
                        .height(42.dp),
                    colors = CardColors(Primary500, Primary500, Primary500, Primary500),
                    shape = RoundedCornerShape(8.dp),

                    ) {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = " Book Now",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }


            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 81.dp
                )
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .verticalScroll(rememberScrollState())
        ) {

            MainTopAppBar(title = "Details") {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Workspace Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(216.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Malone Sharespace",
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color = TextColor
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)

                    )
                    Text(
                        text = " 3.5", fontSize = 12.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = TextColor
                    )

                }

            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Mansoura • AL Eraqi ST", fontSize = 12.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal,
                color = TextColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 3
            ) {
                CategoryChip(R.drawable.baseline_wifi_24, "Fast WIFI"){}
                CategoryChip(R.drawable.baseline_coffee_24, "Coffee Bar"){}
                CategoryChip(R.drawable.baseline_security_24, "Security"){}
                CategoryChip(R.drawable.baseline_meeting_room_24, "Flexible Seating"){}

            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "About",
                fontSize = 20.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.the_workspace),
                fontSize = 12.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal,
                color = TextFieldColor
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Reviews",
                fontSize = 20.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )
            Spacer(modifier = Modifier.height(10.dp))
            ReviewCard()
            ReviewCard()
            ReviewCard()

            Spacer(modifier = Modifier.height(24.dp))

        }
    }


}

@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview() {
    DetailsScreen(rememberNavController())

}


@Composable
fun ReviewCard() {
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
            // Row for Avatar and User Name
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User Avatar
                Image(
                    painterResource(id = R.drawable.imag_profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // User Name
                Text(
                    text = "Amr Nasser",
                    fontSize = 12.sp,
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

            // Review Title


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Everything's fine",
                    fontSize = 14.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = TextColor
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


