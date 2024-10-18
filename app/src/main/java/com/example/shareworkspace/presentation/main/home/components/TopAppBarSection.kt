package com.example.shareworkspace.presentation.main.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shareworkspace.R
import com.example.shareworkspace.presentation.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(title: String, onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = TextColor,
                    fontWeight = FontWeight.Medium
                )
            }

        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent)
    )
}
