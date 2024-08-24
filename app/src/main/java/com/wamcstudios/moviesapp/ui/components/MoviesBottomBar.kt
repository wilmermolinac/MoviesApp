package com.wamcstudios.moviesapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute
import com.wamcstudios.moviesapp.ui.theme.LocalSpacing

@Composable
fun MoviesBottomBar(modifier: Modifier = Modifier, navHostController: NavHostController) {

    val spacing = LocalSpacing.current

    val screens = listOf(
        NavigationItem(
            title = stringResource(id = R.string.home),
            icon = Icons.Outlined.Home,
            iconFilled = Icons.Default.Home,
            navRoute = NavigationRoute.Home
        ), NavigationItem(
            title = stringResource(id = R.string.search),
            icon = Icons.Outlined.Search,
            iconFilled = Icons.Default.Search,
            navRoute = NavigationRoute.Search
        ), NavigationItem(
            title = stringResource(id = R.string.favorites),
            icon = Icons.Outlined.Favorite,
            iconFilled = Icons.Default.Favorite,
            navRoute = NavigationRoute.Favorite
        ), NavigationItem(
            title = stringResource(id = R.string.settings),
            icon = Icons.Outlined.Settings,
            iconFilled = Icons.Default.Settings,
            navRoute = NavigationRoute.Settings
        )
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any() {
        it.navRoute.route == currentDestination?.route
    }

    AnimatedVisibility(
        visible = bottomBarDestination, enter = slideInVertically(
            // Entra deslizándose desde abajo desde offset fullHeight a 0.
            initialOffsetY = { fullHeight -> fullHeight },
        ), exit = slideOutVertically(
            // Sale deslizándose hacia abajo desde offset 0 a fullHeight.
            targetOffsetY = { fullHeight -> fullHeight },
        )
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = spacing.spaceSmall, vertical = spacing.spaceMicroSmall)
                .height(spacing.topAppBarHeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            screens.forEach { navigationItem ->

                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == navigationItem.navRoute.route
                } == true

                val icon = if (isSelected) {
                    navigationItem.iconFilled
                } else {
                    navigationItem.icon
                }

                val iconColor = if (isSelected) {
                    androidx.compose.ui.graphics.Color.White
                } else {
                    MaterialTheme.colorScheme.primary
                }

                val selectedColor = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    androidx.compose.ui.graphics.Color.Transparent
                }

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            color = selectedColor,
                            shape = CircleShape
                        )
                        .clickable {
                            navHostController.navigate(route = navigationItem.navRoute.route) {
                                // Aqui eliminamos el historial de ese Graph
                                /*
                * navHostController.graph.findStartDestination().id como el destino al
                * cual hacer "pop". La función findStartDestination() se utiliza para
                * encontrar el destino de inicio (start destination) del gráfico de
                *  navegación asociado al NavController.
                * */
                                popUpTo(navHostController.graph.findStartDestination().id) {

                                    /*
                    * El parámetro saveState se establece en true, lo que significa que se
                    * intentará salvar (guardar) el estado del destino al que se hace "pop".
                    *  Esto1 puede ser útil si deseas mantener el estado del destino en lugar de
                    * recrearlo cuando se navega nuevamente a él
                    * */
                                    saveState = true
                                }/*
                        * launchSingleTop = true: Indica que si la destinación especificada
                        *  (navigationItem.navigationRoute.route en este caso) ya está en la
                        * parte superior del back stack, no se creará una nueva instancia de esa destinación.
                        * En cambio, se reutilizará la instancia existente y se llamará a onNavigate si está presente.
                        * */
                                launchSingleTop = false

                                /*
                * restoreState = true: Indica que se intentará restaurar el estado de la
                *  destinación si existe un estado guardado para esa destinación en el back stack.
                *  Esto es útil si deseas mantener el estado de la destinación cuando vuelves a ella.
                * */
                                restoreState = true
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {


                    Row(
                        modifier = Modifier.padding(
                            horizontal = spacing.spaceSmall,
                            vertical = spacing.spaceSmall
                        ), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(spacing.iconSize),
                            imageVector = icon,
                            contentDescription = stringResource(
                                id = R.string.icon_navigation,
                                navigationItem.title
                            ), tint = iconColor
                        )


                        AnimatedVisibility(visible = isSelected) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(modifier = Modifier.width(spacing.spaceNanoSmall))
                                Text(
                                    text = navigationItem.title,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = androidx.compose.ui.graphics.Color.White
                                    )
                                )
                            }

                        }

                    }


                }
            }

        }

    }

}