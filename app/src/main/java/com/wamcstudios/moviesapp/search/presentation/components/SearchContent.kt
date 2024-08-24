package com.wamcstudios.moviesapp.search.presentation.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import com.wamcstudios.moviesapp.home.presentation.home.components.MediaItem
import com.wamcstudios.moviesapp.search.presentation.SearchEvent
import com.wamcstudios.moviesapp.search.presentation.SearchState
import com.wamcstudios.moviesapp.ui.theme.LocalSpacing
import com.wamcstudios.moviesapp.ui.theme.WindowInfo
import com.wamcstudios.moviesapp.ui.theme.rememberWindowInfo
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
) {

    val spacing = LocalSpacing.current
    val windowInfo = rememberWindowInfo()
    val focusManager = LocalFocusManager.current

    val refreshState = rememberPullToRefreshState()
    if (refreshState.isRefreshing) {
        LaunchedEffect(key1 = Unit) {
            onEvent(SearchEvent.OnRefresh)
            delay(8000)
            onEvent(SearchEvent.OnRefresh)
            refreshState.endRefresh()

        }
    }


    val columnCount = if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
        2
    } else {
        4
    }

    Box(modifier = modifier
        .fillMaxSize()
        .nestedScroll(refreshState.nestedScrollConnection)
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }) {

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(columnCount),
            contentPadding = PaddingValues(
                top = spacing.spaceLargeExtra * 2,
                bottom = spacing.spaceLargeMedium,
                end = spacing.spaceMedium,
                start = spacing.spaceMedium
            ),
            horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {

            items(state.mediaList.size) { index: Int ->

                val item = state.mediaList[index]
                MediaItem(
                    item =item,
                    onMediaClick = {
                        onEvent(
                            SearchEvent.OnClickMedia(
                                item.id,
                                item.mediaType
                            )
                        )
                    })

                if (index >= state.mediaList.size - 1 && !state.isLoading) {
                    onEvent(SearchEvent.OnPaginate)
                }


            }


        }

        SearchTextField(
            modifier = Modifier.padding(top = spacing.spaceMedium),
            queryValue = state.query,
            onChangeQuery = { onEvent(SearchEvent.ChangeQuery(it)) },
            focusManager = focusManager
        )

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = refreshState,
            contentColor = MaterialTheme.colorScheme.primary
        )


    }
}