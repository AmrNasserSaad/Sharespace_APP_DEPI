package com.example.shareworkspace.core.navigation.routes.model

import androidx.annotation.DrawableRes

data class BottomNavigationItem(
    val title: String,
    @DrawableRes val selectedIcon: Int,
)