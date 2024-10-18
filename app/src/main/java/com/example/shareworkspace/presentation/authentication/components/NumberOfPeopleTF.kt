package com.example.shareworkspace.presentation.authentication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.TextColor
import com.example.shareworkspace.presentation.ui.theme.TextFieldColor

@Composable
fun NumberOfPeopleTF() {
    var textFieldPhone by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = textFieldPhone,
        onValueChange = {
            textFieldPhone = it
        },
        placeholder = {
            Text(
                text = "0 People", color = TextFieldColor, fontFamily = Montserrat
            )
        },
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TextColor,
            unfocusedBorderColor = TextColor,
            focusedLabelColor = TextFieldColor,
        ),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(),

        )
}

@Preview
@Composable
private fun NumberOfPeopleTFPreview() {
    NumberOfPeopleTF()
}