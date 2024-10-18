package com.example.shareworkspace.presentation.main.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shareworkspace.presentation.main.home.components.MainTopAppBar
import com.example.shareworkspace.presentation.main.search.components.WorkspaceCard


@Composable
fun HighestRatedScreen(navController: NavController,navHostController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        MainTopAppBar(title = "Highest Rated") {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WorkspaceCard{ navHostController.navigate("Details_Screen")}
            WorkspaceCard{ navHostController.navigate("Details_Screen")}
            WorkspaceCard{ navHostController.navigate("Details_Screen")}
            WorkspaceCard{ navHostController.navigate("Details_Screen")}
            WorkspaceCard{ navHostController.navigate("Details_Screen")}
            WorkspaceCard{ navHostController.navigate("Details_Screen")}

        }
    }
}
