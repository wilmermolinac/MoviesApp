package com.wamcstudios.moviesapp.navigation.routes

sealed class NavigationRoute(val route: String) {

    object Home : NavigationRoute(route = "home")

    object Detail : NavigationRoute(route = "home/detail")
}