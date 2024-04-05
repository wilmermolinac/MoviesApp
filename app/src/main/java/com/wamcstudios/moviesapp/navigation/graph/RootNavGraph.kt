package com.wamcstudios.moviesapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wamcstudios.aifusion.navigation.utils.navigate
import com.wamcstudios.moviesapp.home.presentation.home.HomeScreen
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute

@Composable
fun RootNavGraph(navHostController: NavHostController, startNavigationRoute: NavigationRoute) {

    NavHost(navController = navHostController, startDestination = startNavigationRoute.route) {

        composable(route = NavigationRoute.Home.route) {

            HomeScreen(onNavigate = navHostController::navigate)


        }

        composable(route = NavigationRoute.Detail.route){


        }
    }

}