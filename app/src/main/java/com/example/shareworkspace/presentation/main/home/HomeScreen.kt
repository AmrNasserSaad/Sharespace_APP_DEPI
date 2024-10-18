package com.example.shareworkspace.presentation.main.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.shareworkspace.R
import com.example.shareworkspace.core.navigation.routes.model.Routes.SEARCH
import com.example.shareworkspace.core.view_state.ViewState
import com.example.shareworkspace.data.model.UserData
import com.example.shareworkspace.data.model.WorkspaceResponse
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.example.shareworkspace.presentation.ui.theme.DisabledContainerColor
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.SearchColor
import com.example.shareworkspace.presentation.ui.theme.SwitchBorderColor
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    navHostController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {

    val viewModel: HomeViewModel = getViewModel()
    val userData by authViewModel.userData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp, end = 16.dp, top = 48.dp,
                bottom = 96.dp
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,

        ) {

        userData?.let {
            HeadSection(it) {
                navController.navigate("NOTIFICATION_SCREEN")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        SearchBarWithIcon {
            navController.navigate(SEARCH)
        }
        Spacer(modifier = Modifier.height(22.dp))
        CustomButtonRow()
        Spacer(modifier = Modifier.height(28.dp))
        TwoText(text = "Nearby Workspaces") {
            navController.navigate("NEARBY_SCREEN")
        }
        Spacer(modifier = Modifier.height(16.dp))
//        LazyRow(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(6) {
//                NearbyWorkspacesCard() {
//                    navHostController.navigate("Details_Screen")
//                }
//            }
//        }
        ViewNearByWorkspaces(viewModel, navController)
        Spacer(modifier = Modifier.height(36.dp))
        TwoText(text = "Highest Rated") {
            navController.navigate("HIGHEST_SCREEN")
        }
        Spacer(modifier = Modifier.height(16.dp))
//        LazyRow(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(6) {
//                RecentWorkspacesCard(R.drawable.back_img) {
//                    navHostController.navigate("Details_Screen")
//                }
//            }
//        }
        ViewWorkspaces(viewModel, navController)
        Spacer(modifier = Modifier.height(16.dp))
        TwoText(text = "Offers") {
            navController.navigate("OFFERS_SCREEN")
        }
        Spacer(modifier = Modifier.height(16.dp))
//        LazyRow(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            items(6) {
//                RecentWorkspacesCard(R.drawable.image) {
//                    navHostController.navigate("Details_Screen")
//                }
//            }
//        }
        ViewWorkspaces(viewModel, navController)
        Spacer(modifier = Modifier.height(16.dp))

    }

}

@Composable
fun ViewNearByWorkspaces(viewModel: HomeViewModel, navController: NavController) {
    val state by viewModel.workspace.collectAsState()
    when (state) {
        is ViewState.Loaded -> {
            val data = (state as ViewState.Loaded).data!!
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(data) { item ->
                    NearbyWorkspacesCard(item) {
                       // navController.navigate("Details_Screen")
                    }
                }
            }
        }

        is ViewState.Error -> {
            val error = (state as ViewState.Error).error
            Text(text = error.message)
        }

        is ViewState.Empty -> {
            Text(text = "Loading...")
        }
    }
}

@Composable
private fun ViewWorkspaces(viewModel: HomeViewModel, navController: NavController) {
    val state by viewModel.workspace.collectAsState()
    when (state) {
        is ViewState.Loaded -> {
            val data = (state as ViewState.Loaded).data!!
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(data) { item ->
                    RecentWorkspacesCard(item) {
                       // navController.navigate("Details_Screen")
                    }
                }
            }
        }

        is ViewState.Error -> {
            val error = (state as ViewState.Error).error
            Text(text = error.message)
        }

        is ViewState.Empty -> {
            Text(text = "Loading...")
        }
    }
}

@Composable
private fun HeadSection(userData: UserData, onClick: () -> Unit) {
    val nameParts = userData.name.split(" ")
    val firstName = nameParts.getOrNull(0) ?: "N/A"
    val lastName = nameParts.getOrNull(1) ?: "N/A"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.imag_profile),
            contentDescription = null,
            modifier = Modifier.size(44.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {

            userData.name?.let {
                Text(
                    text = firstName,
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = TextColor

                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = lastName,
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Medium,
                    color = TextColor
                )
            }
        }
        Spacer(modifier = Modifier.weight(8f))
        Image(
            painter = painterResource(id = R.drawable.ic_badge_notification),
            contentDescription = null,
            modifier = Modifier
                .size(44.dp)
                .clickable { onClick() },
            alignment = Alignment.Center
        )

    }
}

@Composable
fun SearchBarWithIcon(onSearch: () -> Unit) {
    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSearch() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
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
                    text = "Search",
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Normal,
                )
            },
            modifier = Modifier
                .weight(0.90f),

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SearchColor,
                unfocusedContainerColor = SearchColor,
                disabledContainerColor = SearchColor,
                focusedBorderColor = SearchColor,
                unfocusedBorderColor = SearchColor,

                ),
            shape = RoundedCornerShape(24.dp), enabled = false

        )

    }


}

@Composable
fun CustomButtonRow() {
    // Remember the state of each button (enabled/disabled)
    var selectedButton by remember { mutableStateOf("Mix") } // Keeps track of the selected button

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .horizontalScroll(rememberScrollState()) // Add padding around the row
    ) {
        // First button (Mix)
        Button(
            onClick = { selectedButton = "Mix" }, // Set this button as enabled when clicked
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedButton == "Mix") Primary500 else DisabledContainerColor
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(50.dp)
                .padding(end = 8.dp) // Add space between the buttons
        ) {
            Text(
                text = "Mix",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (selectedButton == "Mix") Color.White else TextColor
            )
        }

        // Divider between buttons
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .height(50.dp)
                .background(SearchColor)
        )

        Spacer(modifier = Modifier.width(8.dp)) // Add spacing before the second button

        // Second button (Workspace)
        Button(
            onClick = { selectedButton = "Workspace" }, // Set this button as enabled when clicked
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedButton == "Workspace") Primary500 else DisabledContainerColor
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.height(50.dp)
        ) {
            Text(
                text = "Workspace",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (selectedButton == "Workspace") Color.White else TextColor
            )
        }

        // Third button (Sharespace)
        Spacer(modifier = Modifier.width(8.dp)) // Add spacing between the second and third button
        Button(
            onClick = { selectedButton = "Sharespace" }, // Set this button as enabled when clicked
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedButton == "Sharespace") Primary500 else DisabledContainerColor
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.height(50.dp)
        ) {
            Text(
                text = "Sharespace",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (selectedButton == "Sharespace") Color.White else TextColor
            )
        }
    }
}

@Composable
fun NearbyWorkspacesCard(workspace: WorkspaceResponse, onClick: () -> Unit) {
    var isFavorite by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Surface(
        shadowElevation = 8.dp,
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(12.dp))
            .clickable { onClick() },
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
                    .width(254.dp)
                    .height(180.dp)
            ) {
                //TO DO Async Image
//                Image(
//                    painter = painterResource(id = R.drawable.img_card),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize()
//                )
                AsyncImage(
                    model = if (workspace.image != null) workspace.image else "https://images.pexels.com/photos/380769/pexels-photo-380769.jpeg",
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop


                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RoundedButton("${workspace.type}")
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
                    text = workspace.name,
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
                        text = "${workspace.rate}", fontSize = 12.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = TextColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${workspace.address}", fontSize = 8.sp,
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
                            append("${workspace.hrPrice} LE")
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
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(78.dp)
                        .height(24.dp),
                    colors = CardColors(Primary500, Primary500, Primary500, Primary500),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { onClick() },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_ticket_navbar),
                            contentDescription = "",
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = " Book now",
                            color = Color.White,
                            fontSize = 8.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun TwoText(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
            color = TextColor
        )
        Text(
            text = "View All",
            fontSize = 12.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Normal,
            color = Primary500,
            modifier = Modifier.clickable { onClick() }
        )
    }
}

@Composable
fun RecentWorkspacesCard(workspace: WorkspaceResponse, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(140.dp)
            .height(200.dp)
            .clickable { onClick() }
            .clip(RoundedCornerShape(8)),

        ) {
        //Async Image
//        Image(
//            painter = painterResource(id = workspace.image),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
        AsyncImage(
            model = if (workspace.image != null) workspace.image else "https://images.pexels.com/photos/380769/pexels-photo-380769.jpeg",
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop


        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = "${workspace.hrPrice}", fontSize = 14.sp,
                    fontFamily = Montserrat,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                )

            }
            Spacer(modifier = Modifier.height(120.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${workspace.name}\nSharespace",
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
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
                        text = "${workspace.rate}", fontSize = 12.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                }

            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${workspace.address}", fontSize = 8.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
        }

    }
}

@Composable
fun RoundedButton(text: String) {
    Surface(
        color = Primary500, // Orange color
        shape = RoundedCornerShape(4.dp), // Rounded corners
        modifier = Modifier

            .wrapContentWidth()
            .height(24.dp), // Height of the button
        shadowElevation = 8.dp // Optional: Add some shadow elevation
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp), // Horizontal padding for the text
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White, // White text color
                fontSize = 14.sp, // Adjust the text size
                fontWeight = FontWeight.Medium,// Bold font
                fontFamily = Montserrat

            )
        }
    }
}


