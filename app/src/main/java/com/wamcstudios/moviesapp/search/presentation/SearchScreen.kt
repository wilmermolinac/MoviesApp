package com.wamcstudios.moviesapp.search.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.moviesapp.search.presentation.components.SearchContent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(onNavigate: (UiEvent) -> Unit, viewModel: SearchViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state = viewModel.state
    val snackbarHostState = remember {

        SnackbarHostState()
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { event: UiEvent ->
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
    }) {
        SearchContent(
            modifier = Modifier.padding(paddingValues = it),
            state = state,
            onEvent = { viewModel.onEvent(it) })
    }
}