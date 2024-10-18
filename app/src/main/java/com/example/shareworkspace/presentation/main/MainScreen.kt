package com.example.shareworkspace.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.core.navigation.routes.app_router.MainNavigation
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(navController: NavController,auhViewModel: AuthViewModel) {
    MainNavigation(navHostController = navController,authViewModel = auhViewModel)
}
@Preview(showBackground = true)
@Composable
private fun MainNScreenPreview() {
    MainScreen(navController = rememberNavController(),auhViewModel = getViewModel())
}