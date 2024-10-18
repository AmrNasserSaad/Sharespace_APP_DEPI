package com.example.shareworkspace.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.shareworkspace.core.location_manager.LocationManager
import com.example.shareworkspace.core.navigation.routes.app_router.AppNavigation
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.example.shareworkspace.presentation.ui.theme.ShareWorkspaceTheme
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    // Declare LocationManager and FusedLocationProviderClient
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize AuthViewModel
         val authViewModel: AuthViewModel by viewModels { AuthViewModel.Factory }


        // Initialize FusedLocationProviderClient
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        // Initialize LocationManager and request location permission
        locationManager = LocationManager(this, fusedLocationClient, authViewModel)
        locationManager.requestLocationPermission()



        setContent {
            ShareWorkspaceTheme {
                AppNavigation(authViewModel = authViewModel)
            }
        }
    }
}
