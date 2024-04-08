package com.wamcstudios.moviesapp.home.domain.use_case

data class HomeUseCases(
    val getMediaDetailById: GetMediaDetailById,
    val getMoviesAndTvSeriesList: GetMoviesAndTvSeriesList,
    val updateMediaFavorite: UpdateMediaFavorite,
    val getTrendingList: GetTrendingList,
    val getGenresList: GetGenresList,
)
