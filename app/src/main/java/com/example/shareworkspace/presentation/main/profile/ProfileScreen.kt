package com.example.shareworkspace.presentation.main.profile


import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.R
import com.example.shareworkspace.data.model.UserData
import com.example.shareworkspace.presentation.authentication.AuthState
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldBorderColor
import org.koin.androidx.compose.getViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    navHostController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val authState by authViewModel.authState.collectAsState()
    val userData by authViewModel.userData.collectAsState()

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Unauthenticated -> navHostController.navigate("LOGIN_SCREEN")
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp)

    ) {
        // Profile header with avatar, name, and location
        userData?.let { user ->
            ProfileHeader(R.drawable.img_user, user)
        } ?: run {
            // Show a loading indicator or message if user data is not yet available
            Text(text = "Loading user data...", modifier = Modifier.padding(16.dp))
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Profile options
        ProfileOption(icon = Icons.Default.Person, title = "My details") {
            navController.navigate("MY_DETAILS_SCREEN")
        }
        ProfileOption(icon = Icons.Default.Star, title = "My reviews") {
            navController.navigate("MY_REVIEWS_SCREEN")
        }

        ProfileOption(icon = Icons.Default.Favorite, title = "Bookmarks") {
            navController.navigate("MY_BOOKMARKS_SCREEN")
        }

        ProfileOption(icon = Icons.Default.Phone, title = "Contact us") {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:0123456")
            }
            context.startActivity(intent)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Sign out option at the bottom
        SignOutOption {
            authViewModel.signout()

        }
    }
}

@Composable
fun ProfileHeader(@DrawableRes img: Int, userData: UserData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile image (replace with actual image from URL or resource)
        Image(
            painter = painterResource(img),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))

        // Name and location
        Column {
            Text(
                text = userData.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Mansoura",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }

//        Spacer(modifier = Modifier.weight(1f))
//
//        // Edit icon
//        Icon(
//            imageVector = Icons.Default.Edit,
//            contentDescription = "Edit Profile",
//            modifier = Modifier.clickable { /* Handle edit action */ }
//        )
    }
}

@Composable
fun ProfileOption(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Arrow",
            tint = Color.Gray
        )
    }
    HorizontalDivider(color = Color.LightGray)
}

@Composable
fun SignOutOption(onClick: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDialog = true }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Sign Out",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Sign Out",
            style = MaterialTheme.typography.bodyMedium
        )
    }
    // Review Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Confirm Signout") },
            text = {
                Text(text = "Are You Sure ?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onClick()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Primary500),
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = TextFieldBorderColor),
                ) {
                    Text("Cancel", color = TextColor)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(rememberNavController(), rememberNavController(), authViewModel = getViewModel())
}
