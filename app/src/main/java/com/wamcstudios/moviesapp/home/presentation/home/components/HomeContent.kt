package com.wamcstudios.moviesapp.home.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.wamcstudios.moviesapp.core.common.CategoryMovies
import com.wamcstudios.moviesapp.core.common.CategoryTrending
import com.wamcstudios.moviesapp.home.presentation.home.HomeEvent
import com.wamcstudios.moviesapp.home.presentation.home.HomeState
import com.wamcstudios.moviesapp.ui.theme.LocalSpacing
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(modifier: Modifier = Modifier, state: HomeState, onEvent: (HomeEvent) -> Unit) {

    val spacing = LocalSpacing.current
    val pullToRefreshState = rememberPullToRefreshState()

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(key1 = true) {
            onEvent(HomeEvent.OnPullRefresh)
            delay(7000)
            onEvent(HomeEvent.OnPullRefresh)
            pullToRefreshState.endRefresh()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = spacing.spaceMediumExtra,
                bottom = spacing.spaceMediumExtra
            ), verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
        ) {

            item {

                UpcomingMoviesContainer(
                    mediaMovieList = state.upcomingMovies,
                    onSeeClick = { onEvent(HomeEvent.OnClickSeeAll(CategoryMovies.Upcoming.name)) },
                    onMovieClick = { onEvent(HomeEvent.OnMediaClick(mediaId = it.id, mediaType = it.mediaType)) })

            }


            item {

                TrendingContainer(
                    trendingList = state.trendingList,
                    onSeeClick = {
                        onEvent(HomeEvent.OnClickSeeAll(CategoryTrending.Trending.name))
                    },
                    onMediaClick = { onEvent(HomeEvent.OnMediaClick(it.id, it.mediaType)) })


            }


            item {

                MostPopularContainer(
                    mediaMoviesPopular = state.popularMovies,
                    mediaTvSeriesPopular = state.popularTvSeries,
                    onSeeAllClick = { onEvent(HomeEvent.OnClickSeeAll(CategoryMovies.Popular.name)) },
                    onMediaClick = { onEvent(HomeEvent.OnMediaClick(it.id, it.mediaType)) })

            }
            item {

                TopRatedContainer(
                    mediaMoviesTopRated = state.topRateMovies,
                    mediaTvSeriesTopRated = state.topRateTvSeries,
                    onSeeAllClick = { onEvent(HomeEvent.OnClickSeeAll(CategoryMovies.TopRated.name)) },
                    onMediaClick = { onEvent(HomeEvent.OnMediaClick(it.id, it.mediaType)) })

            }


        }

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState, contentColor = MaterialTheme.colorScheme.primary
        )

    }
}