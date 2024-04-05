package com.wamcstudios.moviesapp.home.data.mapper

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.home.data.local.entity.MediaEntity
import com.wamcstudios.moviesapp.home.data.remote.dto.MediaDto

fun MediaEntity.toMedia(mediaType: String, mediaCategory: String): Media {
    return Media(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        firstAirDate = firstAirDate,
        name = name,
        originCountry = originCountry,
        originalName = originalName,
        mediaCategory = mediaCategory,
        isFavorite = isFavorite,
        mediaType = mediaType
    )
}

fun Media.toMediaEntity(): MediaEntity {
    return MediaEntity(
        id,
        adult,
        backdropPath,
        genreIds,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount,
        firstAirDate,
        name,
        originCountry,
        originalName,
        mediaCategory,
        isFavorite,
        mediaType
    )
}

fun MediaDto.toMediaEntity(mediaCategory: String, mediaType: String): MediaEntity {
    return MediaEntity(
        id = id,
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds ?: emptyList(),
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        firstAirDate = firstAirDate ?: "",
        name = name ?: "",
        originalName = originalName ?: "",
        originCountry = originCountry ?: emptyList(),
        mediaCategory = mediaCategory,
        mediaType = mediaType,
        isFavorite = false
    )
}

fun MediaDto.toMedia(mediaCategory: String, mediaType: String): Media {
    return Media(
        id = id,
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds ?: emptyList(),
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        firstAirDate = firstAirDate ?: "",
        name = name ?: "",
        originalName = originalName ?: "",
        originCountry = originCountry ?: emptyList(),
        mediaCategory = mediaCategory,
        mediaType = mediaType,
        isFavorite = false
    )
}