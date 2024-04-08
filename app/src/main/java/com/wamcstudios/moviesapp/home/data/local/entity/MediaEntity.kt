package com.wamcstudios.moviesapp.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wamcstudios.moviesapp.home.domain.model.KnownFor

@Entity(tableName = "tb_mediaentity")
data class MediaEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<String>,
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

    // People
    val gender: Int,
    val knownFor: List<KnownFor>,
    val knownForDepartment: String,
    val profilePath: String,
)
