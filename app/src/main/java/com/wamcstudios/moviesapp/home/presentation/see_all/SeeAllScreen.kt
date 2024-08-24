package com.wamcstudios.moviesapp.home.presentation.see_all

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.moviesapp.home.presentation.see_all.components.SeeAllContent
import com.wamcstudios.moviesapp.ui.theme.LocalSpacing
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeAllScreen(onNavigate: (UiEvent) -> Unit, viewModel: SeeAllViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { value: UiEvent ->
            when (value) {
                is UiEvent.Navigate -> {
                    onNavigate(value)
                }

                UiEvent.NavigateUp -> {
                    onNavigate(value)
                }

                is UiEvent.PreviousBackStackEntry -> {
                    onNavigate(value)
                }

                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(message = value.message.asString(context))
                }
            }
        }

    }

    Scaffold(topBar = {
        TopAppBar(title = { }, navigationIcon = {
            IconButton(onClick = { onNavigate(UiEvent.NavigateUp) }) {
                Icon(
                    modifier = Modifier.size(spacing.iconSize),
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        })
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) {
        SeeAllContent(
            modifier = Modifier.padding(paddingValues = it),
            state = state,
            onEvent = { viewModel.onEvent(it) })
    }
}