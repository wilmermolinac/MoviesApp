package com.wamcstudios.moviesapp.navigation.graph

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wamcstudios.aifusion.navigation.utils.navigate
import com.wamcstudios.moviesapp.core.common.MediaType
import com.wamcstudios.moviesapp.home.presentation.detail.DetailScreen
import com.wamcstudios.moviesapp.home.presentation.home.HomeScreen
import com.wamcstudios.moviesapp.home.presentation.see_all.SeeAllScreen
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute
import com.wamcstudios.moviesapp.search.presentation.SearchScreen

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startNavigationRoute: NavigationRoute,
) {

    NavHost(
        modifier = modifier.background(color = Color.Transparent),
        navController = navHostController,
        startDestination = startNavigationRoute.route
    ) {

        composable(route = NavigationRoute.Home.route) {

            HomeScreen(onNavigate = navHostController::navigate)


        }

        composable(
            route = NavigationRoute.Detail.route,
            arguments = listOf(navArgument(name = "mediaId") {
                type = NavType.IntType
                nullable = false
                defaultValue = 0

            }, navArgument(name = "mediaType"){
                type = NavType.StringType
                nullable = false
                defaultValue = MediaType.Movie.name
            })
        ) {
            val mediaId = it.arguments?.getInt("mediaId")
            val mediaType = it.arguments?.getInt("mediaType")
            DetailScreen(onNavigate = navHostController::navigate)

        }

        composable(
            route = NavigationRoute.SeeAll.route,
            arguments = listOf(navArgument(name = "category") {
                type = NavType.StringType
                nullable = true
                defaultValue = null

            })
        ) {
            val category = it.arguments?.getString("category")
            SeeAllScreen(onNavigate = navHostController::navigate)
        }

        composable(route = NavigationRoute.Search.route) {

            SearchScreen(onNavigate = navHostController::navigate)

        }

        composable(route = NavigationRoute.Favorites.route) {

        }

        composable(route = NavigationRoute.Settings.route) {

        }
    }

}