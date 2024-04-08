package com.wamcstudios.moviesapp.home.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.moviesapp.core.common.CategoryMovies
import com.wamcstudios.moviesapp.core.common.CategoryTrending
import com.wamcstudios.moviesapp.core.common.CategoryTvSeries
import com.wamcstudios.moviesapp.core.common.Constants
import com.wamcstudios.moviesapp.core.common.MediaType
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.home.domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCases: HomeUseCases) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        load()
    }


    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnPullRefresh -> {
                state = state.copy(isRefresh = !state.isRefresh)
                onRefresh()
            }
        }
    }

    private fun onRefresh(fetchFromRemote: Boolean = true) {
        viewModelScope.launch {
            getGenresList()
            delay(3000)
            getTrendingList(fetchFromRemote)
            getUpcomingMovies(fetchFromRemote)

            getTopRatedMovies(fetchFromRemote)
            getTopRatesTvSeries(fetchFromRemote)

            getMostPopularMovies(fetchFromRemote)
            getMostPopularTvSeries(fetchFromRemote)
        }

    }

    private fun getTrendingList(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            homeUseCases.getTrendingList(
                fetchFromRemote = fetchFromRemote,
                isRefresh = state.isRefresh,
                trendingCategory = CategoryTrending.Trending.name,
                type = Constants.ALL,
                time = Constants.TRENDING_TIME, page = 1, apiKey = Constants.API_KEY
            ).onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _uiEvent.send(UiEvent.ShowSnackBar(message = resource.message!!))
                        state = state.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        state = state.copy(trendingList = resource.data?.sortedBy {
                            it.releaseDate
                        } ?: emptyList())
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getUpcomingMovies(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            homeUseCases.getMoviesAndTvSeriesList(
                fetchFromRemote = fetchFromRemote,
                isRefresh = state.isRefresh,
                mediaType = MediaType.Movie.name,
                mediaCategory = CategoryMovies.Upcoming.name,
                page = 1,
                apiKey = Constants.API_KEY
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
                            upcomingMovies = resource.data?.sortedBy { it.releaseDate }?.reversed()
                                ?: emptyList()
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getTopRatedMovies(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            homeUseCases.getMoviesAndTvSeriesList(
                fetchFromRemote = fetchFromRemote,
                isRefresh = state.isRefresh,
                mediaType = MediaType.Movie.name,
                CategoryMovies.TopRated.name,
                page = 1, Constants.API_KEY
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
                            topRateMovies = resource.data?.sortedBy { it.voteAverage }?.reversed()
                                ?: emptyList()
                        )
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    private fun getTopRatesTvSeries(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            homeUseCases.getMoviesAndTvSeriesList(
                fetchFromRemote = fetchFromRemote,
                isRefresh = state.isRefresh,
                mediaType = MediaType.TvSeries.name,
                mediaCategory = CategoryTvSeries.TopRated.name,
                page = 1, apiKey = Constants.API_KEY
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
                            topRateTvSeries = resource.data?.sortedBy {
                                it.voteAverage
                            } ?: emptyList()
                        )

                    }
                }

            }.launchIn(viewModelScope)
        }
    }

    private fun getMostPopularMovies(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            homeUseCases.getMoviesAndTvSeriesList(
                fetchFromRemote = fetchFromRemote,
                isRefresh = state.isRefresh,
                mediaType = MediaType.Movie.name,
                mediaCategory = CategoryMovies.Popular.name,
                page = 1,
                apiKey = Constants.API_KEY
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
                            popularMovies = resource.data ?: emptyList()
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getMostPopularTvSeries(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            homeUseCases.getMoviesAndTvSeriesList(
                fetchFromRemote = fetchFromRemote,
                isRefresh = state.isRefresh,
                mediaType = MediaType.TvSeries.name,
                mediaCategory = CategoryTvSeries.Popular.name, page = 1, apiKey = Constants.API_KEY
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
                            popularTvSeries = resource.data ?: emptyList()
                        )

                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getGenresList() {
        viewModelScope.launch {
            homeUseCases.getGenresList(state.isRefresh).onEach { resource ->
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
                            state.copy(isLoading = false, genresList = resource.data ?: emptyList())
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun load(fetchFromRemote: Boolean = false) {

        viewModelScope.launch {
            getGenresList()
            delay(3000)
            getTrendingList(fetchFromRemote)
            getUpcomingMovies(fetchFromRemote)

            getTopRatedMovies(fetchFromRemote)
            getTopRatesTvSeries(fetchFromRemote)

            getMostPopularMovies(fetchFromRemote)
            getMostPopularTvSeries(fetchFromRemote)
        }


    }


}