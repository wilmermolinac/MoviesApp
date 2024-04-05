package com.wamcstudios.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute
import com.wamcstudios.moviesapp.navigation.graph.RootNavGraph
import com.wamcstudios.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            navHostController = rememberNavController()

            MoviesAppTheme {

                RootNavGraph(
                    navHostController = navHostController,
                    startNavigationRoute = NavigationRoute.Home
                )


            }


        }
    }
}
