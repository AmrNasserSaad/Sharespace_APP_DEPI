package com.example.shareworkspace.presentation.main.search

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.R
import com.example.shareworkspace.presentation.main.search.components.WorkspaceCard
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.SearchColor
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(navController: NavController,navHostController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 48.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Search",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Mutable state to hold the search bar's text
        var searchText by remember { mutableStateOf("") }

        // Search Bar
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search Icon",
                    tint = TextFieldColor
                )
            },
            placeholder = {
                Text(
                    text = "Find Workspace, Sharespace, or rooms",
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SearchColor,
                unfocusedContainerColor = SearchColor,
                disabledContainerColor = SearchColor,
                focusedBorderColor = SearchColor,
                unfocusedBorderColor = SearchColor,
            ),
            shape = RoundedCornerShape(24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Your History Section
        Text(
            text = "Your history",
            fontFamily = Montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 3
        ) {
            // Pass the searchText setter as a parameter to update the text field
            HistoryChip("Mansoura") { searchText = it }
            HistoryChip("Cairo") { searchText = it }
            HistoryChip("Workspace") { searchText = it }
            HistoryChip("Tanta") { searchText = it }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search by Category Section
        Text(
            text = "Search by category",
            fontFamily = Montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = 3
        ) {
            CategoryChip(R.drawable.ic_workspace,"Workspace"){ searchText = it }
            CategoryChip(R.drawable.ic_info,"Offers"){ searchText = it }
            CategoryChip(R.drawable.baseline_meeting_room_24,"Sharespace"){ searchText = it }
            CategoryChip(R.drawable.ic_national_id,"Rooms"){ searchText = it }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your Search",
            fontFamily = Montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WorkspaceCard{ navHostController.navigate("Details_Screen")}


        }
    }
}

@Composable
fun HistoryChip(label: String, onChipClick: (String) -> Unit) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(width = 1.dp, TextFieldColor),
        modifier = Modifier
            .padding(4.dp)
            .clickable { onChipClick(label) } // Call the click handler with the chip label
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(8.dp),
            color = TextColor,
            fontSize = 12.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun CategoryChip(@DrawableRes icon: Int, label: String, onChipClick: (String) -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, TextFieldColor),
        modifier = Modifier
            .padding(4.dp)
            .clickable { onChipClick(label) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = TextFieldColor,
                modifier = Modifier.padding(start = 8.dp)
            )

            Text(
                text = label,
                modifier = Modifier.padding(8.dp),
                color = TextFieldColor,
                fontSize = 12.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    SearchScreen(rememberNavController(), rememberNavController())
}
