package com.wamcstudios.moviesapp.navigation.routes

sealed class NavigationRoute(val route: String) {

    object Home : NavigationRoute(route = "home")
    object Detail : NavigationRoute(route = "home/detail/{mediaId}/{mediaType}") {

        fun passData(mediaId: Int, mediaType: String) = "home/detail/${mediaId}/${mediaType}"

    }

    object SeeAll : NavigationRoute(route = "home/see_all/?{category}") {
        fun passData(category: String) = "home/see_all/?${category}"
    }

    object Search : NavigationRoute(route = "search")
    object Favorites : NavigationRoute(route = "favorites")
    object Settings : NavigationRoute("settings")
}