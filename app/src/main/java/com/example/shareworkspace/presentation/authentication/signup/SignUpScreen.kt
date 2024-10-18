package com.example.shareworkspace.presentation.authentication.signup

import android.util.Patterns
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shareworkspace.R
import com.example.shareworkspace.presentation.authentication.AuthState
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldBorderColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor


@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> navController.navigate("MAIN_SCREEN") {
                popUpTo("SIGN_UP_SCREEN") {
                    inclusive = true
                }
                popUpTo("LOGIN_SCREEN") {
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

    var textFieldFristName by remember {
        mutableStateOf("")
    }
    var textFieldLastName by remember {
        mutableStateOf("")
    }
    var textFieldId by remember {
        mutableStateOf("")
    }
    var textFieldEmail by remember {
        mutableStateOf("")
    }
    var textFieldPhone by remember {
        mutableStateOf("")
    }
    var textFieldPassword by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember { mutableStateOf(false) }


    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6 // Adjust this as per your password requirements
    }

    fun validateInputs(): Boolean {
        return when {
            textFieldFristName.isBlank() -> {
                Toast.makeText(context, "First name is required", Toast.LENGTH_SHORT).show()
                false
            }

            textFieldLastName.isBlank() -> {
                Toast.makeText(context, "Last name is required", Toast.LENGTH_SHORT).show()
                false
            }

            textFieldId.length != 14 || !textFieldId.all { it.isDigit() } -> {
                Toast.makeText(context, "National ID must be 14 digits", Toast.LENGTH_SHORT).show()
                false
            }

            textFieldPhone.length != 11 || !textFieldPhone.all { it.isDigit() } -> {
                Toast.makeText(context, "Phone number must be 11 digits", Toast.LENGTH_SHORT).show()
                false
            }

            !isValidEmail(textFieldEmail) -> {
                Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
                false
            }

            !isValidPassword(textFieldPassword) -> {
                Toast.makeText(
                    context,
                    "Password must be at least 6 characters",
                    Toast.LENGTH_SHORT
                ).show()
                false
            }

            else -> true
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 46.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,

        ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Start)
                .clickable {
                    navController.navigate("LOGIN_SCREEN")
                }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Create account",
            fontSize = 24.sp,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
            color = TextColor
        )
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = textFieldFristName,
                onValueChange = {
                    textFieldFristName = it
                },
                placeholder = {
                    Text(
                        text = "Frist name", color = TextFieldColor, fontFamily = Montserrat
                    )
                },
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TextFieldBorderColor,
                    unfocusedBorderColor = TextFieldBorderColor,
                    focusedLabelColor = TextFieldColor,
                ),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.weight(0.5f),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "email icon",
                        modifier = Modifier.size(24.dp),
                        tint = TextFieldColor
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = textFieldLastName,
                onValueChange = {
                    textFieldLastName = it
                },
                placeholder = {
                    Text(
                        text = "Last name", color = TextFieldColor, fontFamily = Montserrat
                    )
                },
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TextFieldBorderColor,
                    unfocusedBorderColor = TextFieldBorderColor,
                    focusedLabelColor = TextFieldColor,
                ),
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.weight(0.5f),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "email icon",
                        modifier = Modifier.size(24.dp),
                        tint = TextFieldColor
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = textFieldPhone,
            onValueChange = {
                textFieldPhone = it
            },
            placeholder = {
                Text(
                    text = "+20*********", color = TextFieldColor, fontFamily = Montserrat
                )
            },
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TextFieldBorderColor,
                unfocusedBorderColor = TextFieldBorderColor,
                focusedLabelColor = TextFieldColor,
            ),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_call),
                    contentDescription = "email icon",
                    modifier = Modifier.size(24.dp),
                    tint = TextFieldColor
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = textFieldId,
            onValueChange = {
                textFieldId = it
            },
            placeholder = {
                Text(
                    text = "National ID", color = TextFieldColor, fontFamily = Montserrat
                )
            },
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TextFieldBorderColor,
                unfocusedBorderColor = TextFieldBorderColor,
                focusedLabelColor = TextFieldColor,
            ),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_national_id),
                    contentDescription = "email icon",
                    modifier = Modifier.size(24.dp),
                    tint = TextFieldColor
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = textFieldEmail,
            onValueChange = {
                textFieldEmail = it
            },
            placeholder = {
                Text(
                    text = "abc@email.com", color = TextFieldColor, fontFamily = Montserrat
                )
            },
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


        Spacer(modifier = Modifier.height(16.dp))

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

        Spacer(modifier = Modifier.height(64.dp))
        Button(
            onClick = {
                if (validateInputs()) {
                    authViewModel.signup(
                        email = textFieldEmail,
                        password = textFieldPassword,
                        name = "$textFieldFristName $textFieldLastName",
                        phone = textFieldPhone,
                        nationalId = textFieldId,
                    )
                }
            },
            enabled = authState != AuthState.Loading,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary500),

            ) {
            Text(
                text = "Sign up",
                fontSize = 24.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account? ",
                fontSize = 16.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = " login",
                fontSize = 16.sp,
                color = Primary500,
                fontFamily = Montserrat,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    navController.navigate("LOGIN_SCREEN")
                }
            )

        }

    }
}


//@Preview(showBackground = true)
//@Composable
//fun SignUpScreenPreview(modifier: Modifier = Modifier) {
//    SignUpScreen(navController = rememberNavController(), authViewModel = getViewModel())
//}