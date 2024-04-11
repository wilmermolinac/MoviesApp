package com.wamcstudios.moviesapp.home.presentation.detail.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.wamcstudios.aifusion.core.desingsystem.theme.LocalSpacing
import com.wamcstudios.moviesapp.home.presentation.detail.DetailEvent
import com.wamcstudios.moviesapp.home.presentation.detail.DetailState
import kotlinx.coroutines.delay

private const val TAG = "DetailContent"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    state: DetailState,
    onEvent: (DetailEvent) -> Unit,
) {

    val spacing = LocalSpacing.current
    val pullToRefreshState = rememberPullToRefreshState()

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(key1 = true) {
            onEvent(DetailEvent.OnRefresh)
            delay(5000)
            onEvent(DetailEvent.OnRefresh)
            pullToRefreshState.endRefresh()

        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(connection = pullToRefreshState.nestedScrollConnection)
    ) {

        Log.d(TAG, "MediaItem: ${state.mediaItem}")

        DetailItem(
            modifier = Modifier,
            mediaItem = state.mediaItem,
            onClickFavorite = { onEvent(DetailEvent.OnCLickFavorite) })


        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
            contentColor = MaterialTheme.colorScheme.primary
        )

    }
}