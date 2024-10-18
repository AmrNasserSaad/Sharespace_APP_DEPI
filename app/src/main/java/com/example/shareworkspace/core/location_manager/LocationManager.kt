package com.example.shareworkspace.core.location_manager

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.shareworkspace.data.model.User
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class LocationManager(
    private val activity: ComponentActivity,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val authViewModel: AuthViewModel
) {

    // Function to request location permission
    private val requestLocationPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            getLastKnownLocation()  // If permission is granted, retrieve location
        } else {
            // Handle the case when the user denies the permission (optional handling)
        }
    }

    // Function to initiate permission request
    fun requestLocationPermission() {
        requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    // Function to retrieve the last known location
    private fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->

                location?.let {
                    // Use location data (latitude and longitude)
                    authViewModel.updateUserLocation(it.latitude, it.longitude)
                    val user = User(latitude = it.latitude, longitude = it.longitude)
                    // You can now use or save the user object with location data
                    // For example, pass it back to ViewModel or other logic
                } ?: run {
                    // Handle the case where location is null (e.g., GPS is off)
                    println("Location is null")
                }
            }.addOnFailureListener {
                // Handle any errors while retrieving location
            }
        }
    }
}