package com.wamcstudios.moviesapp.home.data.mapper

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.toLocalDate
import com.wamcstudios.moviesapp.home.data.local.entity.GenreEntity
import com.wamcstudios.moviesapp.home.data.local.entity.MediaEntity
import com.wamcstudios.moviesapp.home.data.remote.dto.BelongsToCollectionDto
import com.wamcstudios.moviesapp.home.data.remote.dto.CreatedByDto
import com.wamcstudios.moviesapp.home.data.remote.dto.GenreDto
import com.wamcstudios.moviesapp.home.data.remote.dto.KnownForDto
import com.wamcstudios.moviesapp.home.data.remote.dto.LastEpisodeToAirDto
import com.wamcstudios.moviesapp.home.data.remote.dto.MediaDto
import com.wamcstudios.moviesapp.home.data.remote.dto.NetworkDto
import com.wamcstudios.moviesapp.home.data.remote.dto.NextEpisodeToAirDto
import com.wamcstudios.moviesapp.home.data.remote.dto.ProductionCompanyDto
import com.wamcstudios.moviesapp.home.data.remote.dto.ProductionCountryDto
import com.wamcstudios.moviesapp.home.data.remote.dto.SeasonDto
import com.wamcstudios.moviesapp.home.data.remote.dto.SpokenLanguageDto
import com.wamcstudios.moviesapp.home.domain.model.BelongsToCollection
import com.wamcstudios.moviesapp.home.domain.model.CreatedBy
import com.wamcstudios.moviesapp.home.domain.model.Genre
import com.wamcstudios.moviesapp.home.domain.model.KnownFor
import com.wamcstudios.moviesapp.home.domain.model.LastEpisodeToAir
import com.wamcstudios.moviesapp.home.domain.model.Network
import com.wamcstudios.moviesapp.home.domain.model.NextEpisodeToAir
import com.wamcstudios.moviesapp.home.domain.model.ProductionCompany
import com.wamcstudios.moviesapp.home.domain.model.ProductionCountry
import com.wamcstudios.moviesapp.home.domain.model.Season
import com.wamcstudios.moviesapp.home.domain.model.SpokenLanguage
import java.time.LocalDate

fun MediaEntity.toMedia(): Media {
    return Media(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genres = genresIds,
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
        isFavorite = isFavorite ?: false,
        mediaType = this.mediaType,
        gender,
        knownFor,
        knownForDepartment,
        profilePath,
        belongsToCollectionDetail,
        budgetDetail,
        genresListDetail,
        homepageDetail,
        imdbIdDetail,
        productionCompaniesDetail,
        productionCountriesDetail,
        revenueDetail,
        runtimeDetail,
        spokenLanguageDetail,
        statusDetail,
        taglineDetail,
        createdByTvDetail,
        episodeRunTimeTvDetail,
        inProductionTvDetail,
        languagesTvDetail,
        lastAirDateTvDetail,
        lastEpisodeToAirTvDetail,
        networksTvDetail,
        nextEpisodeToAirTvDetail,
        numberOfEpisodesTvDetail,
        numberOfSeasonsTvDetail,
        seasonsTvDetail,
        typeTvDetail
    )
}

/*fun Media.toMediaEntity(): MediaEntity {
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
        mediaType,
        gender = gender,
        knownFor,
        knownForDepartment,
        profilePath,
        belongsToCollectionDetail,
        budgetDetail,
        genresListDetail,
        homepageDetail,
        imdbIdDetail,
        productionCompaniesDetail,
        productionCountriesDetail,
        revenueDetail,
        runtimeDetail,
        spokenLanguageDetail,
        statusDetail,
        taglineDetail,
        createdByTvDetail,
        episodeRunTimeTvDetail,
        inProductionTvDetail,
        languagesTvDetail,
        lastAirDateTvDetail,
        lastEpisodeToAirTvDetail,
        networksTvDetail,
        nextEpisodeToAirTvDetail,
        numberOfEpisodesTvDetail,
        numberOfSeasonsTvDetail, seasonsTvDetail,
        typeTvDetail
    )
}*/

fun MediaDto.toMediaEntity(mediaCategory: String, mediaType: String): MediaEntity {
    return MediaEntity(id = id,
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genresIds = genreIds?.map {
            it
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
        knownFor = this.knownForListDto?.map {
            it.toKnownFor()
        } ?: emptyList(),
        gender = gender ?: 0,
        knownForDepartment = knownForDepartment ?: "",
        profilePath = profilePath ?: "",
        belongsToCollectionDetail = belongsToCollectionDetailDto?.toBelongsToCollection(),
        budgetDetail = budgetDetail ?: 0,
        genresListDetail = genresDetail?.map {
            it.toGenre()
        } ?: emptyList(),
        homepageDetail = homepageDetail ?: "",
        productionCompaniesDetail = productionCompaniesDetail?.map {
            it.toProductionCompany()
        } ?: emptyList(),
        imdbIdDetail = imdbIdDetail ?: "",
        revenueDetail = revenueDetail ?: 0,
        runtimeDetail = runtimeDetail ?: 0,
        spokenLanguageDetail = spokenLanguageDtosDetail?.map {
            it.toSpokenLanguage()
        } ?: emptyList(),
        productionCountriesDetail = productionCountriesDetail?.map {
            it.toProductionCountry()
        } ?: emptyList(),
        statusDetail = statusDetail ?: "",
        createdByTvDetail = createdByDtoTvDetail?.map {
            it.toCreatedBy()
        } ?: emptyList(),
        taglineDetail = taglineDetail ?: "",
        inProductionTvDetail = inProductionTvDetail ?: false,
        languagesTvDetail = languagesTvDetail ?: emptyList(),
        lastAirDateTvDetail = lastAirDateTvDetail ?: "",
        lastEpisodeToAirTvDetail = lastEpisodeToAirDtoTvDetail?.toLastEpisodeToAirDto(),
        episodeRunTimeTvDetail = episodeRunTimeTvDetail ?: emptyList(),
        nextEpisodeToAirTvDetail = nextEpisodeToAirTvDetail?.toNextEpisodeToAir(),
        networksTvDetail = networksTvDetail?.map {
            it.toNetwork()
        } ?: emptyList(),
        numberOfSeasonsTvDetail = numberOfSeasonsTvDetail ?: 0,
        seasonsTvDetail = seasonsTvDetail?.map {
            it.toSeason()
        } ?: emptyList(),
        numberOfEpisodesTvDetail = numberOfEpisodesTvDetail ?: 0,
        typeTvDetail = typeTvDetail ?: "")
}

fun MediaDto.toMedia(mediaCategory: String, mediaType: String): Media {
    return Media(id = id,
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
        knownFor = this.knownForListDto?.map {
            it.toKnownFor()
        } ?: emptyList(),
        gender = gender ?: 0,
        knownForDepartment = knownForDepartment ?: "",
        profilePath = profilePath ?: "",
        belongsToCollectionDetail = belongsToCollectionDetailDto?.toBelongsToCollection(),
        budgetDetail = budgetDetail ?: 0,
        genresListDetail = genresDetail?.map {
            it.toGenre()
        } ?: emptyList(),
        homepageDetail = homepageDetail ?: "",
        productionCompaniesDetail = productionCompaniesDetail?.map {
            it.toProductionCompany()
        } ?: emptyList(),
        imdbIdDetail = imdbIdDetail ?: "",
        revenueDetail = revenueDetail ?: 0,
        runtimeDetail = runtimeDetail ?: 0,
        spokenLanguageDetail = spokenLanguageDtosDetail?.map {
            it.toSpokenLanguage()
        } ?: emptyList(),
        productionCountriesDetail = productionCountriesDetail?.map {
            it.toProductionCountry()
        } ?: emptyList(),
        statusDetail = statusDetail ?: "",
        createdByTvDetail = createdByDtoTvDetail?.map {
            it.toCreatedBy()
        } ?: emptyList(),
        taglineDetail = taglineDetail ?: "",
        inProductionTvDetail = inProductionTvDetail ?: false,
        languagesTvDetail = languagesTvDetail ?: emptyList(),
        lastAirDateTvDetail = lastAirDateTvDetail ?: "",
        lastEpisodeToAirTvDetail = lastEpisodeToAirDtoTvDetail?.toLastEpisodeToAirDto(),
        episodeRunTimeTvDetail = episodeRunTimeTvDetail ?: emptyList(),
        nextEpisodeToAirTvDetail = nextEpisodeToAirTvDetail?.toNextEpisodeToAir(),
        networksTvDetail = networksTvDetail?.map {
            it.toNetwork()
        } ?: emptyList(),
        numberOfSeasonsTvDetail = numberOfSeasonsTvDetail ?: 0,
        seasonsTvDetail = seasonsTvDetail?.map {
            it.toSeason()
        } ?: emptyList(),
        numberOfEpisodesTvDetail = numberOfEpisodesTvDetail ?: 0,
        typeTvDetail = typeTvDetail ?: "")
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

fun BelongsToCollectionDto.toBelongsToCollection(): BelongsToCollection {
    return BelongsToCollection(backdropPath ?: "", id ?: 0, name ?: "", posterPath ?: "")
}

fun CreatedByDto.toCreatedBy(): CreatedBy {
    return com.wamcstudios.moviesapp.home.domain.model.CreatedBy(
        creditId ?: "", gender ?: 0, id ?: 0, name ?: "", profilePath ?: ""
    )
}

fun LastEpisodeToAirDto.toLastEpisodeToAirDto(): LastEpisodeToAir {
    return LastEpisodeToAir(
        airDate ?: "",
        episodeNumber ?: 0,
        episodeType ?: "",
        id ?: 0,
        name ?: "",
        overview ?: "",
        productionCode ?: "",
        runtime ?: 0,
        seasonNumber ?: 0,
        showId ?: 0,
        stillPath ?: "",
        voteAverage ?: 0.0,
        voteCount ?: 0
    )
}

fun NetworkDto.toNetwork(): Network {
    return Network(id ?: 0, logoPath ?: "", name ?: "", originCountry ?: "")
}

fun NextEpisodeToAirDto.toNextEpisodeToAir(): NextEpisodeToAir {
    return NextEpisodeToAir(
        airDate ?: "",
        episodeNumber ?: 0,
        episodeType ?: "",
        id ?: 0,
        name ?: "",
        overview ?: "",
        productionCode ?: "",
        runtime ?: 0,
        seasonNumber ?: 0,
        showId ?: 0,
        stillPath ?: "",
        voteAverage ?: 0.0,
        voteCount ?: 0
    )
}


fun ProductionCompanyDto.toProductionCompany(): ProductionCompany {
    return ProductionCompany(id ?: 0, logoPath ?: "", name ?: "", originCountry ?: "")
}

fun ProductionCountryDto.toProductionCountry(): ProductionCountry {
    return ProductionCountry(iso31661 ?: "", name ?: "")
}

fun SeasonDto.toSeason(): Season {
    return Season(
        airDate ?: "",
        episodeCount ?: 0,
        id ?: 0,
        name ?: "",
        overview ?: "",
        posterPath ?: "",
        seasonNumber ?: 0,
        voteAverage ?: 0.0
    )
}

fun SpokenLanguageDto.toSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(englishName ?: "", iso6391 ?: "", name ?: "")
}


