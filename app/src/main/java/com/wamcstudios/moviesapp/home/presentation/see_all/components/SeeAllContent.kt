package com.wamcstudios.moviesapp.home.presentation.see_all.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing
import com.wamcstudios.aifusion.core.desingsystem.theme.WindowInfo
import com.wamcstudios.aifusion.core.desingsystem.theme.rememberWindowInfo
import com.wamcstudios.moviesapp.core.common.CategoryMovies
import com.wamcstudios.moviesapp.core.common.CategoryTrending
import com.wamcstudios.moviesapp.core.ui.components.MediaItemShimmer
import com.wamcstudios.moviesapp.home.presentation.home.components.MediaItem
import com.wamcstudios.moviesapp.home.presentation.see_all.SeeAllEvent
import com.wamcstudios.moviesapp.home.presentation.see_all.SeeAllState
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeAllContent(
    modifier: Modifier = Modifier,
    state: SeeAllState,
    onEvent: (SeeAllEvent) -> Unit,
) {

    val windowInfo = rememberWindowInfo()
    val spacing = LocalSpacing.current

    val countColumns = if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
        2
    } else {
        4
    }


    val pullToRefreshState = rememberPullToRefreshState()

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(key1 = true) {

            onEvent(SeeAllEvent.OnRefresh)
            delay(8000)
            onEvent(SeeAllEvent.OnRefresh)
            pullToRefreshState.endRefresh()

        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(countColumns),
            contentPadding = PaddingValues(
                top = spacing.spaceLargeMedium,
                bottom = spacing.spaceMediumExtra
            ),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {

            if (state.mediaList.isNullOrEmpty()) {
                items(10) {
                    MediaItemShimmer(onMediaClick = {})
                }
            } else {
                items(state.mediaList.size) { index ->

                    MediaItem(
                        item = state.mediaList[index],
                        onMediaClick = { onEvent(SeeAllEvent.OnClickMediaItem(state.mediaList[index].id)) })

                    if (index >= state.mediaList.size - 1 && !state.isLoading) {
                        onEvent(SeeAllEvent.OnPaginate)
                    }
                }
            }


        }


        if (state.category != CategoryTrending.Trending.name && state.category != CategoryMovies.Upcoming.name) {

            TabRow(
                modifier = Modifier.align(Alignment.TopCenter),
                selectedTabIndex = state.selectedTabIndex
            ) {
                state.tabs.forEachIndexed { index, title ->

                    val isSelected = state.selectedTabIndex == index

                    Tab(selected = isSelected, onClick = {
                        onEvent(
                            SeeAllEvent.OnCLickTabItem(
                                mediaType = title,
                                selectedTabIndex = index
                            )
                        )
                    }) {

                        val colorBackground = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.background
                        }
                        Text(
                            text = title.uppercase(),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
                        )

                    }

                }


            }
        }


        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState, contentColor = MaterialTheme.colorScheme.primary
        )

    }
}