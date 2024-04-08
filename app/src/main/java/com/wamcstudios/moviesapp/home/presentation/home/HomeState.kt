package com.wamcstudios.moviesapp.home.presentation.home

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.home.domain.model.Genre
import com.wamcstudios.moviesapp.home.domain.use_case.GetGenresList

data class HomeState(
    val isLoading: Boolean = false,
    val isRefresh: Boolean = false,
    val trendingList: List<Media> = emptyList(),
    val upcomingMovies: List<Media> = emptyList(),
    val topRateMovies: List<Media> = emptyList(),
    val topRateTvSeries: List<Media> = emptyList(),
    val popularMovies: List<Media> = emptyList(),
    val popularTvSeries: List<Media> = emptyList(), val genresList: List<Genre> = emptyList(),
)
