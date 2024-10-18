package com.example.shareworkspace.presentation.authentication.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shareworkspace.R
import com.example.shareworkspace.presentation.authentication.AuthState
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.SwitchBorderColor
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldBorderColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> navController.navigate("MAIN_SCREEN") {
                popUpTo("LOGIN_SCREEN") {
                    inclusive = true
                }
                popUpTo("SIGN_UP_SCREEN"){
                    inclusive = true
                }
            }
            is AuthState.Error -> Toast.makeText(
                context,
                (authState as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    var textFieldEmail by remember { mutableStateOf("") }
    var textFieldPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 56.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Login",
            fontSize = 24.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
            color = TextColor
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Email TextField with validation
        OutlinedTextField(
            value = textFieldEmail,
            onValueChange = {
                textFieldEmail = it
                emailError = if (it.isValidEmail()) null else "Invalid email format"
            },
            placeholder = {
                Text(
                    text = "abc@email.com", color = TextFieldColor, fontFamily = Montserrat
                )
            },
            isError = emailError != null,
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TextFieldBorderColor,
                unfocusedBorderColor = TextFieldBorderColor,
                focusedLabelColor = TextFieldColor,
            ),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email_at),
                    contentDescription = "email icon",
                    modifier = Modifier.size(24.dp),
                    tint = TextFieldColor
                )
            }
        )
        if (emailError != null) {
            Text(text = emailError ?: "", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField with validation
        OutlinedTextField(
            value = textFieldPassword,
            onValueChange = {
                textFieldPassword = it
            },
            placeholder = {
                Text(
                    text = "Your password", color = TextFieldColor, fontFamily = Montserrat
                )
            },
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TextFieldBorderColor,
                unfocusedBorderColor = TextFieldBorderColor,
                focusedLabelColor = TextFieldColor,
            ),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = if (passwordVisible) KeyboardType.Text else KeyboardType.Password
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = TextFieldColor
                )
            },
            trailingIcon = {
                val icon = if (passwordVisible) R.drawable.ic_show_pass else R.drawable.ic_hide_pass
                val description = if (passwordVisible) "Hide password" else "Show password"

                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = description,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { passwordVisible = !passwordVisible },
                    tint = TextFieldColor
                )
            }
        )
        if (passwordError != null) {
            Text(text = passwordError ?: "", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Remember Me Switch
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SwitchWithCustomColors()
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Remember Me",
                fontSize = 14.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(46.dp))

        // Login Button with validation
        Button(
            onClick = {
                if (emailError == null && passwordError == null) {
                    authViewModel.login(textFieldEmail, textFieldPassword)
                } else {
                    Toast.makeText(context, "Please correct the errors", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = authState != AuthState.Loading,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary500,
                disabledContainerColor = Primary500.copy(alpha = 0.5f)
            )
        ) {
            Text(
                text = "Login",
                fontSize = 24.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Sign-up link
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Create a new account",
                fontSize = 16.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = " Sign up",
                fontSize = 16.sp,
                color = Primary500,
                fontFamily = Montserrat,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    navController.navigate("SIGN_UP_SCREEN")
                }
            )
        }
    }
}

// Email validation extension function
fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

@Composable
fun SwitchWithCustomColors() {
    var checked by remember { mutableStateOf(false) }
    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = Primary500,
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = SwitchBorderColor,
            uncheckedBorderColor = SwitchBorderColor
        )
    )
}




@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(), authViewModel = getViewModel())
}