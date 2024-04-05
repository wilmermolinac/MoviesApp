package com.wamcstudios.moviesapp.core.domain.model

data class Media(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,

    val firstAirDate: String,
    val name: String,
    val originCountry: List<String>,
    val originalName: String,

    val mediaCategory: String,
    val isFavorite: Boolean,
    val mediaType: String,
)
