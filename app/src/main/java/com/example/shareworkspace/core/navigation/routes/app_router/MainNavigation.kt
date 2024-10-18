package com.example.shareworkspace.core.navigation.routes.app_router

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.R
import com.example.shareworkspace.core.navigation.routes.model.BottomNavigationItem
import com.example.shareworkspace.core.navigation.routes.model.Routes.BOOKING
import com.example.shareworkspace.core.navigation.routes.model.Routes.HOME
import com.example.shareworkspace.core.navigation.routes.model.Routes.PROFILE
import com.example.shareworkspace.core.navigation.routes.model.Routes.SEARCH
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.example.shareworkspace.presentation.main.booking.BookingScreen
import com.example.shareworkspace.presentation.main.common.HighestRatedScreen
import com.example.shareworkspace.presentation.main.common.NearbyScreen
import com.example.shareworkspace.presentation.main.common.NotificationScreen
import com.example.shareworkspace.presentation.main.common.OffersScreen
import com.example.shareworkspace.presentation.main.home.HomeScreen
import com.example.shareworkspace.presentation.main.profile.ProfileScreen
import com.example.shareworkspace.presentation.main.profile.profile_screens.ProfileBookmarks
import com.example.shareworkspace.presentation.main.profile.profile_screens.ProfileDetails
import com.example.shareworkspace.presentation.main.profile.profile_screens.ProfileReviews
import com.example.shareworkspace.presentation.main.search.SearchScreen
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigation(navHostController: NavController, authViewModel: AuthViewModel) {

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            MainNavigationBar(navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { _ ->

        NavHost(
            navController = navController,
            startDestination = HOME,
        ) {
            composable(route = HOME) {
                HomeScreen(navController, navHostController,authViewModel)
            }
            composable(route = SEARCH) {
                SearchScreen(navController = navController, navHostController = navHostController)
            }
            composable(route = BOOKING) {
                BookingScreen(navController)
            }
            composable(route = PROFILE) {
                ProfileScreen(navController, navHostController, authViewModel)
            }
            composable(route = "NOTIFICATION_SCREEN") {
                NotificationScreen(navController)
            }
            composable(route = "NEARBY_SCREEN") {
                NearbyScreen(navController, navHostController)
            }
            composable(route = "HIGHEST_SCREEN") {
                HighestRatedScreen(navController, navHostController)
            }
            composable(route = "OFFERS_SCREEN") {
                OffersScreen(navController, navHostController)
            }
            composable(route = "MY_DETAILS_SCREEN") {
                ProfileDetails(navController,authViewModel)
            }
            composable(route = "MY_REVIEWS_SCREEN") {
                ProfileReviews(navController)
            }
            composable(route = "MY_BOOKMARKS_SCREEN") {
                ProfileBookmarks(navController)
            }


        }
    }
}


@Composable
fun MainNavigationBar(navController: NavController) {

    val navigationItems = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = R.drawable.ic_home_navbar,
        ),
        BottomNavigationItem(
            title = "Search",
            selectedIcon = R.drawable.ic_search_navbar,

            ),
        BottomNavigationItem(
            title = "Booking",
            selectedIcon = R.drawable.ic_ticket_navbar,

            ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = R.drawable.ic_profile_navbar,

            ),


        )

    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }

    NavigationBar(

        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),

        ) {

        navigationItems.forEachIndexed { index, item ->


            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.title)
                },
                icon = {


                    Row(

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {


                        Icon(
                            painter = painterResource(id = item.selectedIcon),
                            contentDescription = "icon",
                            tint = if (selectedItemIndex == index) Color.White else Color.Gray,
                            modifier = Modifier.size(18.dp)

                        )
                        AnimatedVisibility(visible = selectedItemIndex == index) {
                            Text(
                                text = item.title,
                                color = Color.White,
                                fontSize = 10.sp,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }


                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Primary500,
                    selectedIconColor = Primary500,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.White,
                ),

                )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BottomNavPreview() {
    MainNavigationBar(navController = rememberNavController())
}