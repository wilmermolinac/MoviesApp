package com.wamcstudios.moviesapp.favorite.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.favorite.domain.use_case.FavoriteUseCases
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteUseCases: FavoriteUseCases) :
    ViewModel() {

    var state by mutableStateOf(FavoriteState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var mediaListJob :Job? = null

    init {
        getAllFavoriteMediaList()
    }


    fun load(){
        getAllFavoriteMediaList()
    }

    fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.OnMediaClick -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            route = NavigationRoute.Detail.passData(
                                mediaId = event.mediaId,
                                mediaType = event.mediaType
                            )
                        )
                    )
                }
            }
        }
    }

    private fun getAllFavoriteMediaList() {
        viewModelScope.launch {
            favoriteUseCases.getAllMediaFavorite().onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        state = state.copy(isLoading = false)
                        _uiEvent.send(UiEvent.ShowSnackBar(message = resource.message!!))
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)

                    }

                    is Resource.Success -> {
                        state =
                            state.copy(isLoading = false, mediaList = resource.data ?: emptyList())

                    }
                }
            }.launchIn(viewModelScope)
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}