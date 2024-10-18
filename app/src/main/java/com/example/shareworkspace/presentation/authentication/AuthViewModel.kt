package com.example.shareworkspace.presentation.authentication

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.shareworkspace.ShareWorkSpaceApplication
import com.example.shareworkspace.data.data_source.local.prefs.PreferenceHelper
import com.example.shareworkspace.data.model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel(private val helper: PreferenceHelper) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    private val _userData = MutableStateFlow<UserData?>(null) // Holds user data
    val userData: StateFlow<UserData?> = _userData.asStateFlow()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        auth.currentUser?.let { user ->
            fetchUserData(user.uid)
            _authState.value = AuthState.Authenticated
        } ?: run {
            _authState.value = AuthState.Unauthenticated
        }
    }

    // Login function with Firebase Authentication
    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid ?: return@addOnCompleteListener
                    fetchUserData(userId)
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Login failed")
                }
            }
    }

    // Signup function with Firebase Authentication and user data saving
    fun signup(email: String, password: String, name: String, phone: String, nationalId: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.user?.let { firebaseUser ->
                        saveUserData(firebaseUser.uid, name, phone, nationalId, email)
                    }
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Signup failed")
                }
            }
    }

    // Save user data in Firebase Realtime Database
    private fun saveUserData(
        uid: String,
        name: String,
        phone: String,
        nationalId: String,
        email: String
    ) {
        val user = mapOf(
            "uid" to uid,
            "name" to name,
            "phone" to phone,
            "nationalId" to nationalId,
            "email" to email
        )
        dbRef.child(uid).setValue(user).addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                _authState.value = AuthState.Error("Failed to save user data")
            }
        }
    }

    // Fetch user data from Firebase Database
    private fun fetchUserData(uid: String) {
        dbRef.child(uid).get().addOnSuccessListener { snapshot ->
            val user = snapshot.getValue(UserData::class.java)
            _userData.update { user }
        }.addOnFailureListener {
            // Handle failure
            _authState.value = AuthState.Error("Failed to fetch user data")
        }
    }

    // Sign out function
    fun signout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        _userData.update { null } // Clear user data on sign out
    }

    // Update user location in shared preferences
    fun updateUserLocation(latitude: Double, longitude: Double) {
        helper.lat = latitude
        helper.lon = longitude
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = extras[APPLICATION_KEY] as ShareWorkSpaceApplication
                return AuthViewModel(
                    PreferenceHelper(
                        application.applicationContext.getSharedPreferences(
                            "ShareSpaceApp",
                            Context.MODE_PRIVATE
                        )
                    )
                ) as T
            }
        }
    }
}

// Define different authentication states
sealed class AuthState {
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}
