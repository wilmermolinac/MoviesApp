package com.wamcstudios.moviesapp.home.presentation.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.moviesapp.core.common.MediaType
import com.wamcstudios.moviesapp.home.presentation.detail.components.DetailContent
import com.wamcstudios.moviesapp.ui.theme.LocalSpacing
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(onNavigate: (UiEvent) -> Unit, viewModel: DetailViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state = viewModel.state
    val spacing = LocalSpacing.current
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }

                UiEvent.NavigateUp -> {
                    onNavigate(event)
                }

                is UiEvent.PreviousBackStackEntry -> {
                    onNavigate(event)
                }

                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(message = event.message.asString(context))
                }
            }
        }
    }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }, topBar = {

        val title = if (state.mediaItem?.mediaType == MediaType.Movie.name) {
            state.mediaItem.title
        } else {
            state.mediaItem?.originalName
        }
        TopAppBar(title = {
            Text(
                modifier = Modifier,
                text = title ?: "",
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.tertiary),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center
            )
        }, navigationIcon = {
            IconButton(onClick = { onNavigate(UiEvent.NavigateUp) }) {
                Icon(
                    modifier = Modifier.size(spacing.iconSize),
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        })
    }) {
        DetailContent(modifier = Modifier.padding(paddingValues = it),
            state = state,
            onEvent = { viewModel.onEvent(it) })
    }
}