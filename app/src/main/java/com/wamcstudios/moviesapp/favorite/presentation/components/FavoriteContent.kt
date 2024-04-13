package com.wamcstudios.moviesapp.favorite.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing
import com.wamcstudios.aifusion.core.desingsystem.theme.WindowInfo
import com.wamcstudios.aifusion.core.desingsystem.theme.rememberWindowInfo
import com.wamcstudios.moviesapp.favorite.presentation.FavoriteEvent
import com.wamcstudios.moviesapp.favorite.presentation.FavoriteState
import com.wamcstudios.moviesapp.home.presentation.home.components.MediaItem

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    state: FavoriteState,
    onEvent: (FavoriteEvent) -> Unit,
) {

    val spacing = LocalSpacing.current
    val windowInfo = rememberWindowInfo()
    val columnCount = if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
        2
    } else {
        4
    }

    Box(modifier = modifier.fillMaxSize()) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            contentPadding = PaddingValues(
                top = spacing.spaceMediumExtra,
                end = spacing.spaceMedium,
                bottom = spacing.spaceMediumExtra, start = spacing.spaceMedium
            ),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {

            items(state.mediaList) { item ->

                MediaItem(
                    item = item,
                    onMediaClick = {
                        onEvent(
                            FavoriteEvent.OnMediaClick(
                                mediaId = item.id,
                                mediaType = item.mediaType
                            )
                        )
                    })
            }

        }


        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }


    }
}