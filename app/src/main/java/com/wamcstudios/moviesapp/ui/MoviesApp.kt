package com.wamcstudios.moviesapp.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wamcstudios.moviesapp.navigation.graph.RootNavGraph
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute
import com.wamcstudios.moviesapp.ui.components.MoviesBottomBar

@Composable
fun MoviesApp(
    navHostController: NavHostController,
    startNavigationRoute: NavigationRoute,
) {

    Scaffold(
        bottomBar = {
            MoviesBottomBar(
                modifier = Modifier.fillMaxWidth(),
                navHostController = navHostController
            )
        },
        contentWindowInsets = WindowInsets(
            left = 0.dp,
            top = 0.dp,
            right = 0.dp,
            bottom = 0.dp
        )
    ) {
        RootNavGraph(
            modifier = Modifier
                .padding(paddingValues = it)
                .consumeWindowInsets(paddingValues = it),
            navHostController = navHostController,
            startNavigationRoute = startNavigationRoute
        )
    }
}