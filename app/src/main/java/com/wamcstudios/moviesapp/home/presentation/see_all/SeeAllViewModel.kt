package com.wamcstudios.moviesapp.home.presentation.see_all

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.moviesapp.core.common.CategoryTrending
import com.wamcstudios.moviesapp.core.common.Constants
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.core.utils.isOnline
import com.wamcstudios.moviesapp.home.domain.use_case.HomeUseCases
import com.wamcstudios.moviesapp.navigation.routes.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SeeAllViewModel"

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val homeUseCases: HomeUseCases, @ApplicationContext private val context: Context,
) :
    ViewModel() {

    var state by mutableStateOf(SeeAllState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {

        validateNetworkConnect()
        getNavigateData()
    }

    fun onEvent(event: SeeAllEvent) {
        when (event) {
            is SeeAllEvent.OnClickMediaItem -> {

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

            SeeAllEvent.OnRefresh -> {
                validateNetworkConnect()

                state = state.copy(isRefresh = !state.isRefresh, page = 1)

                when (state.category) {
                    CategoryTrending.Trending.name -> {
                        getTrendingMediaItems(state.isConnected)
                    }

                    else -> {
                        getMediaItems(state.isConnected)
                    }

                }
            }

            SeeAllEvent.OnPaginate -> {
                //state = state.copy(page = state.page + 1)

                when (state.category) {
                    CategoryTrending.Trending.name -> {
                        getTrendingMediaItems(state.isConnected)
                    }

                    else -> {
                        getMediaItems(state.isConnected)
                    }

                }
            }

            is SeeAllEvent.OnCLickTabItem -> {
                state = state.copy(
                    selectedTabIndex = event.selectedTabIndex,
                    type = event.mediaType,
                    mediaList = emptyList(),
                    page = 1
                )

                when (state.category) {
                    CategoryTrending.Trending.name -> {
                        getTrendingMediaItems(state.isConnected)
                    }

                    else -> {
                        getMediaItems(state.isConnected)
                    }

                }

            }
        }
    }

    private fun getNavigateData() {
        savedStateHandle.get<String>("category")?.let { data ->

            when (data) {
                CategoryTrending.Trending.name -> {
                    state = state.copy(type = Constants.ALL, category = data)
                    getTrendingMediaItems(state.isConnected)

                }

                else -> {
                    state = state.copy(category = data)
                    getMediaItems(fetchFromRemote = state.isConnected)
                }
            }
        }

    }

    private fun getMediaItems(fetchFromRemote: Boolean) {

        viewModelScope.launch {

            validateNetworkConnect()

            homeUseCases.getMoviesAndTvSeriesList(
                fetchFromRemote,
                state.isRefresh,
                state.type,
                state.category,
                state.page, Constants.API_KEY
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

                        val remoteList = resource.data?.toList() ?: emptyList()

                        val mediaList = state.mediaList + remoteList
                        state =
                            state.copy(
                                isLoading = false,
                                mediaList = mediaList, page = state.page + 1
                            )
                        Log.d(TAG, "MediaList: ${state.mediaList}")
                    }
                }
            }.launchIn(viewModelScope)
        }


    }

    private fun getTrendingMediaItems(fetchFromRemote: Boolean) {
        viewModelScope.launch {

            validateNetworkConnect()

            homeUseCases.getTrendingList(
                fetchFromRemote,
                state.isRefresh,
                state.category,
                state.type,
                Constants.TRENDING_TIME, state.page, Constants.API_KEY
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

                        val remoteList = resource.data?.toList() ?: emptyList()

                        val mediaList = state.mediaList + remoteList

                        state =
                            state.copy(
                                isLoading = false,
                                mediaList = mediaList, page = state.page + 1
                            )
                        Log.d(TAG, "MediaList: ${state.mediaList}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun validateNetworkConnect() {
        state = state.copy(isConnected = isOnline(context))

    }
}