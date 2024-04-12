package com.wamcstudios.moviesapp.core.domain.model

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

data class Media(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<Any>,
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
    val isFavorite: Boolean = false,
    val mediaType: String,

    // People
    val gender: Int,
    val knownFor: List<KnownFor>,
    val knownForDepartment: String,
    val profilePath: String,

    // Detail
    val belongsToCollectionDetail: BelongsToCollection?,
    val budgetDetail: Int,
    val genresListDetail: List<Genre>,
    val homepageDetail: String,
    val imdbIdDetail: String,
    val productionCompaniesDetail: List<ProductionCompany>,
    val productionCountriesDetail: List<ProductionCountry>,
    val revenueDetail: Long,
    val runtimeDetail: Int,
    val spokenLanguageDetail: List<SpokenLanguage>,
    val statusDetail: String,
    val taglineDetail: String,


    val createdByTvDetail: List<CreatedBy>,
    val episodeRunTimeTvDetail: List<Int>,
    val inProductionTvDetail: Boolean,
    val languagesTvDetail: List<String>,
    val lastAirDateTvDetail: String,
    val lastEpisodeToAirTvDetail: LastEpisodeToAir?,
    val networksTvDetail: List<Network>,
    val nextEpisodeToAirTvDetail: NextEpisodeToAir?,
    val numberOfEpisodesTvDetail: Int,
    val numberOfSeasonsTvDetail: Int,
    val seasonsTvDetail: List<Season>,
    val typeTvDetail: String,

    )
