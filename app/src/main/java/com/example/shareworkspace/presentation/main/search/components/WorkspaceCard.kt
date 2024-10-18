package com.example.shareworkspace.presentation.main.search.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shareworkspace.R
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.TextColor

@Composable
fun WorkspaceCard(onClick: () -> Unit) {
    Card(

        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Row(modifier = Modifier
            .clickable { onClick() }
            .padding(16.dp)) {

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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchResultsScreen() {
    WorkspaceCard {}
}
