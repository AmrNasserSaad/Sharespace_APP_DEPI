package com.example.shareworkspace.presentation.authentication.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.shareworkspace.presentation.ui.theme.Montserrat
import com.example.shareworkspace.presentation.ui.theme.Primary500


@Preview(showBackground = true)
@Composable
private fun CommonButtonWithTextPreview() {
    CommonButtonWithText("Amr")
    CommonButtonWithText("AKM")

}


@Composable
fun CommonButtonWithText(text: String) {
    Button(onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Primary500)) {
        Text(text = text, fontFamily = Montserrat ,
            fontSize = 20.sp , fontWeight = FontWeight.Medium  )

    }
}