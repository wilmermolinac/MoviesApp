package com.wamcstudios.moviesapp.home.data.mapper

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.toDateString
import com.wamcstudios.moviesapp.core.utils.toLocalDate
import com.wamcstudios.moviesapp.home.data.local.entity.GenreEntity
import com.wamcstudios.moviesapp.home.data.local.entity.MediaEntity
import com.wamcstudios.moviesapp.home.data.remote.dto.GenreDto
import com.wamcstudios.moviesapp.home.data.remote.dto.KnownForDto
import com.wamcstudios.moviesapp.home.data.remote.dto.MediaDto
import com.wamcstudios.moviesapp.home.domain.model.Genre
import com.wamcstudios.moviesapp.home.domain.model.KnownFor
import java.time.LocalDate

fun MediaEntity.toMedia(): Media {
    return Media(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genres = genres,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate.toLocalDate(),
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        firstAirDate = firstAirDate,
        name = name,
        originCountry = originCountry,
        originalName = originalName,
        mediaCategory = this.mediaCategory,
        isFavorite = isFavorite,
        mediaType = this.mediaType, gender, knownFor, knownForDepartment, profilePath
    )
}

fun Media.toMediaEntity(): MediaEntity {
    return MediaEntity(
        id,
        adult,
        backdropPath,
        genres,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate.toDateString(),
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
        mediaType, gender = gender, knownFor, knownForDepartment, profilePath
    )
}

fun MediaDto.toMediaEntity(mediaCategory: String, mediaType: String): MediaEntity {
    return MediaEntity(
        id = id,
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genres = genreIds?.map {
            it.toString()
        } ?: emptyList(),
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
        mediaType = this.mediaType ?: mediaType,
        isFavorite = false,
        knownFor = this.knownForListDto?.map {
            it.toKnownFor()
        } ?: emptyList(),
        gender = gender ?: 0,
        knownForDepartment = knownForDepartment ?: "",
        profilePath = profilePath ?: ""
    )
}

fun MediaDto.toMedia(mediaCategory: String, mediaType: String): Media {
    return Media(
        id = id,
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genres = genreIds?.map {
            it.toString()
        } ?: emptyList(),
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate?.toLocalDate() ?: LocalDate.now(),
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        firstAirDate = firstAirDate ?: "",
        name = name ?: "",
        originalName = originalName ?: "",
        originCountry = originCountry ?: emptyList(),
        mediaCategory = mediaCategory,
        mediaType = this.mediaType ?: mediaType,
        isFavorite = false, knownFor = this.knownForListDto?.map {
            it.toKnownFor()
        } ?: emptyList(),
        gender = gender ?: 0,
        knownForDepartment = knownForDepartment ?: "",
        profilePath = profilePath ?: ""
    )
}

fun KnownForDto.toKnownFor(): KnownFor {
    return KnownFor(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        firstAirDate = firstAirDate ?: "",
        genreIds = genreIds ?: emptyList(),
        id = id ?: 0,
        mediaType = mediaType ?: "",
        name = name ?: "",
        originCountry = originCountry ?: emptyList(),
        originalName = originalName ?: "",
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0
    )
}

fun GenreDto.toGenreEntity(): GenreEntity {
    return GenreEntity(id, name)
}

fun GenreDto.toGenre(): Genre {
    return Genre(id, name)
}

fun GenreEntity.toGenre(): Genre {
    return Genre(id, name)
}

fun Genre.toGenreEntity(): GenreEntity {
    return GenreEntity(id, name)
}