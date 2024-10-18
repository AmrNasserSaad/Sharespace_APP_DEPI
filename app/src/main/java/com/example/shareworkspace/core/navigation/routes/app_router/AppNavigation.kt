package com.example.shareworkspace.core.navigation.routes.app_router

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.core.di.viewModelModule
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.example.shareworkspace.presentation.authentication.login.LoginScreen
import com.example.shareworkspace.presentation.authentication.signup.SignUpScreen
import com.example.shareworkspace.presentation.main.MainScreen
import com.example.shareworkspace.presentation.main.confirmation.ConfirmScreen
import com.example.shareworkspace.presentation.main.confirmation.RequestScreen
import com.example.shareworkspace.presentation.main.details.DetailsScreen

@Composable
fun AppNavigation(authViewModel : AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LOGIN_SCREEN") {
        composable("LOGIN_SCREEN") {
            LoginScreen(navController = navController,authViewModel = authViewModel)
        }
        composable("SIGN_UP_SCREEN") {
            SignUpScreen(navController = navController,authViewModel =authViewModel)
        }

        composable("MAIN_SCREEN") {
            MainScreen(navController = navController, auhViewModel = authViewModel)
        }
        composable(route = "Details_Screen") {
            DetailsScreen(navController)
        }
        composable(route = "request_screen") {
            RequestScreen(navController)
        }
        composable(route = "Confirm_Screen") {
            ConfirmScreen(navController)
        }

    }
}