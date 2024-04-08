package com.wamcstudios.moviesapp.home.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing
import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.ui.components.MediaItemShimmer
import com.wamcstudios.moviesapp.core.ui.components.MoviesAndTvSeriesContainer

@Composable
fun MostPopularContainer(
    modifier: Modifier = Modifier,
    mediaMoviesPopular: List<Media>,
    mediaTvSeriesPopular: List<Media>, onSeeAllClick: () -> Unit, onMediaClick: (Media) -> Unit,
) {
    val spacing = LocalSpacing.current

    MoviesAndTvSeriesContainer(modifier = modifier,
        title = stringResource(id = R.string.most_popular),
        onSeeAllClick = { onSeeAllClick() }) {

        Column {
            Text(
                modifier = Modifier.padding(
                    start = spacing.spaceSmall,
                    bottom = spacing.spaceMicroSmall
                ),
                text = stringResource(id = R.string.movies),
                style = MaterialTheme.typography.titleMedium
            )

            LazyRow(
                contentPadding = PaddingValues(
                    end = spacing.spaceSmall,
                    start = spacing.spaceSmall
                ), horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
            ) {

                if (mediaMoviesPopular.isEmpty()) {
                    items(10) {
                        MediaItemShimmer {

                        }
                    }
                } else {
                    items(mediaMoviesPopular) { item: Media ->
                        MediaItem(item = item, onMediaClick = { onMediaClick(item) })
                    }
                }


            }

            Spacer(modifier = Modifier.height(spacing.spaceMicroSmall))
            Text(
                modifier = Modifier.padding(
                    start = spacing.spaceSmall,
                    bottom = spacing.spaceMicroSmall
                ),
                text = stringResource(id = R.string.tv),
                style = MaterialTheme.typography.titleMedium
            )

            LazyRow(
                contentPadding = PaddingValues(
                    start = spacing.spaceSmall,
                    end = spacing.spaceSmall
                ), horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
            ) {

                if (mediaTvSeriesPopular.isEmpty()) {
                    items(10) {
                        MediaItemShimmer {

                        }
                    }
                } else {
                    items(mediaTvSeriesPopular) { item: Media ->
                        MediaItem(item = item, onMediaClick = { onMediaClick(item) })
                    }
                }


            }
        }

    }


}