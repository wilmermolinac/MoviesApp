package com.wamcstudios.moviesapp.navigation.routes

sealed class NavigationRoute(val route: String) {

    object Home : NavigationRoute(route = "home")
    object Detail : NavigationRoute(route = "home/detail/{mediaId}") {

        fun passData(mediaId: Int) = "home/detail/${mediaId}"

    }

    object Search : NavigationRoute(route = "search")
    object Favorites : NavigationRoute(route = "favorites")
    object Settings : NavigationRoute("settings")
}