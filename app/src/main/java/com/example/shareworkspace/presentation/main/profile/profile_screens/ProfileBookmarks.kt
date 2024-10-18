package com.example.shareworkspace.presentation.main.profile.profile_screens

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.R
import com.example.shareworkspace.presentation.main.home.RoundedButton
import com.example.shareworkspace.presentation.main.home.components.MainTopAppBar
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.SwitchBorderColor
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor

@Composable
fun ProfileBookmarks(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        MainTopAppBar(title = "My Bookmarks") {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Screen content
        FavWorkspaceCard()
        FavWorkspaceCard()
        FavWorkspaceCard()
        FavWorkspaceCard()
        FavWorkspaceCard()


    }
}

@Composable
fun FavWorkspaceCard() {

    var isFavorite by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(12.dp))
            .padding(bottom = 24.dp),
        color = SwitchBorderColor
    ) {
        Column(
            modifier = Modifier
                .width(254.dp)
                .height(300.dp)
                .clip(shape = RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_card),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RoundedButton("Sharespace")
                    Card(
                        onClick = {
                            // Toggle favorite state
                            isFavorite = !isFavorite
                            // Show toast message
                            Toast.makeText(
                                context,
                                if (isFavorite) "Added to Favorites" else "Removed from Favorites",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        modifier = Modifier.size(24.dp),
                        colors = CardColors(
                            TextFieldColor,
                            TextFieldColor,
                            TextFieldColor,
                            TextFieldColor
                        ),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bookmark),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .size(12.dp),
                            tint = if (isFavorite) Primary500 else Color.White // Orange when favorite, white otherwise
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            // Remaining content...
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Malone Sharespace",
                    fontSize = 12.sp,
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
                text = "Mansoura • AL Eraqi ST", fontSize = 8.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal,
                color = TextColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            )
            Spacer(modifier = Modifier.height(21.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontFamily = Montserrat,
                                color = Primary500
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileBookmarksPreview() {
    ProfileBookmarks(rememberNavController())
}