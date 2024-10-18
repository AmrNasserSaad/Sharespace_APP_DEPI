package com.example.shareworkspace.presentation.main.booking

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.R
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldBorderColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor

@Composable
fun BookingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 48.dp,
                bottom = 96.dp
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Booking",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        BookingCard()
        BookingCard()
        BookingCard()
        BookingCard()
        BookingCard()


    }
}

@Preview(showBackground = true)
@Composable
private fun BookingScreenPreview() {
    BookingScreen(navController = rememberNavController())
}

@Composable
fun BookingCard() {
    var showDialog by remember { mutableStateOf(false) }
    var reviewText by remember { mutableStateOf("") }
    var reviewRating by remember { mutableStateOf("") }
    val context = LocalContext.current

    Card(

        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            Image(
                painter = painterResource(id = R.drawable.card_img),
                contentDescription = "Workspace Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Error Workspace",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    color = TextColor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Mansoura • AL Galaa ST", fontSize = 14.sp,
                    color = TextColor, fontFamily = Montserrat
                )
                Spacer(modifier = Modifier.height(28.dp))

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
                                color = TextColor,
                                fontFamily = Montserrat
                            )
                        ) {
                            append(" /Hour")
                        }

                    }
                )


            }

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
            Spacer(modifier = Modifier.height(42.dp))


        }
        // BookingCard UI

        Row(modifier = Modifier.padding(16.dp)) {
            // Add existing BookingCard content here

            // Add review button
            Surface(
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(width = 1.dp, TextFieldColor),
                color = TextFieldBorderColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, bottom = 12.dp)
            ) {
                TextButton(onClick = {
                    showDialog = true
                }) {
                    Text(
                        text = "add review", fontSize = 14.sp,
                        color = TextColor, fontFamily = Montserrat
                    )
                }
            }
        }


        // Review Dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Add Review") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = reviewText,
                            onValueChange = { reviewText = it },
                            label = { Text("Your Review") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = reviewRating,
                            onValueChange = { reviewRating = it },
                            label = { Text("Rate") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        // Show toast on save
                        Toast.makeText(context, "Review Added", Toast.LENGTH_SHORT).show()
                        showDialog = false
                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Primary500),
                    ) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }
                        ,
                        colors = ButtonDefaults.buttonColors(containerColor = TextFieldBorderColor),) {
                        Text("Cancel", color = TextColor)
                    }
                }
            )
        }
    }
}