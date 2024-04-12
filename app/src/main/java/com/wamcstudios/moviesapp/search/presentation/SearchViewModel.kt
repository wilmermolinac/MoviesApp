package com.wamcstudios.moviesapp.search.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.moviesapp.core.common.Constants
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.core.utils.isOnline
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute
import com.wamcstudios.moviesapp.search.domain.use_case.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val searchUseCases: SearchUseCases,
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        validateNetworkConnect()
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.ChangeQuery -> {
                state = state.copy(query = event.query)
                validateNetworkConnect()
                getMediaList(state.isConnect)
            }

            is SearchEvent.OnClickMedia -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            route = NavigationRoute.Detail.passData(
                                event.mediaId,
                                event.mediaType
                            )
                        )
                    )
                }
            }

            SearchEvent.OnRefresh -> {
                validateNetworkConnect()
                state = state.copy(isRefresh = !state.isRefresh, page = 1)
                getMediaList(fetchFromRemote = state.isConnect)

            }

            SearchEvent.OnPaginate -> {
                validateNetworkConnect()
                getMediaListPaginate(state.isConnect)
            }
        }
    }

    private fun getMediaList(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            searchUseCases.getSearchList(
                query = state.query,
                page = state.page,
                apiKey = Constants.API_KEY,
                fetchFromRemote = fetchFromRemote, isRefresh = state.isRefresh
            ).onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        state = state.copy(isLoading = false)
                        _uiEvent.send(UiEvent.ShowSnackBar(message = resource.message!!))
                    }

                    is Resource.Loading -> {
                        state =
                            state.copy(isLoading = true)
                    }

                    is Resource.Success -> {


                        state =
                            state.copy(
                                isLoading = false,
                                mediaList = resource.data ?: emptyList()
                            )
                    }
                }

            }.launchIn(viewModelScope)
        }
    }

    private fun getMediaListPaginate(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            searchUseCases.getSearchList(
                query = state.query,
                page = state.page,
                apiKey = Constants.API_KEY,
                fetchFromRemote = fetchFromRemote, isRefresh = state.isRefresh
            ).onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        state = state.copy(isLoading = false)
                        _uiEvent.send(UiEvent.ShowSnackBar(message = resource.message!!))
                    }

                    is Resource.Loading -> {
                        state =
                            state.copy(isLoading = true)
                    }

                    is Resource.Success -> {

                        val remoteMediaList = resource.data ?: emptyList()
                        val totalMediaList = state.mediaList + remoteMediaList

                        state =
                            state.copy(
                                isLoading = false,
                                mediaList = totalMediaList,
                                page = state.page + 1
                            )
                    }
                }

            }.launchIn(viewModelScope)
        }
    }


    private fun validateNetworkConnect() {
        state = state.copy(isConnect = isOnline(context))

    }
}