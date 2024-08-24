package com.wamcstudios.moviesapp.home.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.ui.components.MediaItemShimmer
import com.wamcstudios.moviesapp.core.ui.components.MoviesAndTvSeriesContainer
import com.wamcstudios.moviesapp.ui.theme.LocalSpacing

@Composable
fun TrendingContainer(
    modifier: Modifier = Modifier,
    trendingList: List<Media>,
    onSeeClick: () -> Unit, onMediaClick: (Media) -> Unit,
) {

    val spacing = LocalSpacing.current

    MoviesAndTvSeriesContainer(modifier = modifier,
        title = stringResource(id = R.string.trending_now),
        onSeeAllClick = { onSeeClick() }) {
        Column {
            LazyRow(
                contentPadding = PaddingValues(
                    start = spacing.spaceSmall,
                    end = spacing.spaceSmall
                ), horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
            ) {

                if (trendingList.isEmpty()){
                    items(10){
                        MediaItemShimmer(onMediaClick = {})
                    }
                }else{
                    items(trendingList) { item ->
                        MediaItem(item = item, onMediaClick = {onMediaClick(item)})
                    }
                }



            }
        }

    }
}