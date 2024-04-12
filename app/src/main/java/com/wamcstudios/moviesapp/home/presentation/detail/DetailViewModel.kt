package com.wamcstudios.moviesapp.home.presentation.detail

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.moviesapp.core.common.Constants
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.core.utils.isOnline
import com.wamcstudios.moviesapp.home.domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
private const val TAG = "DetailViewModel"
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val homeUseCases: HomeUseCases, @ApplicationContext private val context: Context,
) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        validateNetworkConnect()
        getNavigateData()
    }


    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.OnRefresh -> {
                state = state.copy(isRefresh = !state.isRefresh)
            }

            DetailEvent.OnCLickFavorite -> {
                viewModelScope.launch {

                    homeUseCases.updateMediaFavorite(
                        mediaId = state.mediaId,
                        isFavorite = !state.isFavorite
                    )


                    getMediaDetail(state.isConnected)
                }
            }
        }
    }

    private fun getNavigateData() {
        savedStateHandle.get<Int>("mediaId")?.let { data ->
            Log.d(TAG, "MediaId: $state")
            if (data > 0) {
                state = state.copy(mediaId = data)
                getMediaDetail(fetchFromRemote = state.isConnected)
            } else {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateUp)
                }
            }
        }
    }

    private fun getMediaDetail(fetchFromRemote: Boolean) {

        viewModelScope.launch {
            homeUseCases.getMediaDetailById(
                mediaId = state.mediaId,
                fetchFromRemote = fetchFromRemote,
                isRefresh = state.isRefresh, apiKey = Constants.API_KEY
            ).onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        state = state.copy(isLoading = false)
                        _uiEvent.send(UiEvent.ShowSnackBar(message = resource.message!!))
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            mediaItem = resource.data,
                            isFavorite = resource.data?.isFavorite ?: false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }

    }

    private fun validateNetworkConnect() {
        state = state.copy(isConnected = isOnline(context))
    }

}