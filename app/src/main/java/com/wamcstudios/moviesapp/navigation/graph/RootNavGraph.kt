package com.wamcstudios.moviesapp.navigation.graph

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wamcstudios.aifusion.navigation.utils.navigate
import com.wamcstudios.moviesapp.home.presentation.home.HomeScreen
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute

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

        composable(route = NavigationRoute.Detail.route) {


        }

        composable(route = NavigationRoute.Search.route){

        }

        composable(route = NavigationRoute.Favorites.route){

        }

        composable(route = NavigationRoute.Settings.route){

        }
    }

}