package com.wamcstudios.moviesapp.core.domain.model

import com.wamcstudios.moviesapp.home.domain.model.KnownFor
import java.time.LocalDate

data class Media(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: LocalDate,
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

    // People
    val gender: Int,
    val knownFor: List<KnownFor>,
    val knownForDepartment: String,
    val profilePath: String,
)
