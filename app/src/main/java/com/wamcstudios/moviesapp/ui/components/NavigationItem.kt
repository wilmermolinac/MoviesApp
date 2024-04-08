package com.wamcstudios.moviesapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.wamcstudios.moviesapp.core.utils.UiText
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val iconFilled: ImageVector,
    val navRoute: NavigationRoute,
)
