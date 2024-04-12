package com.wamcstudios.moviesapp.home.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MediaDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,

    // TvSeries
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: List<String>?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("media_type")
    val mediaType: String?,


    // People
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("known_for")
    val knownForListDto: List<KnownForDto>?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("profile_path")
    val profilePath: String?,



    // Detail
    @SerializedName("belongs_to_collection")
    val belongsToCollectionDetailDto: BelongsToCollectionDto?,
    @SerializedName("budget")
    val budgetDetail: Int?,
    @SerializedName("genres")
    val genresDetail: List<GenreDto>?,
    @SerializedName("homepage")
    val homepageDetail: String?,
    @SerializedName("imdb_id")
    val imdbIdDetail: String?,
    @SerializedName("production_companies")
    val productionCompaniesDetail: List<ProductionCompanyDto>?,
    @SerializedName("production_countries")
    val productionCountriesDetail: List<ProductionCountryDto>?,
    @SerializedName("revenue")
    val revenueDetail: Long?,
    @SerializedName("runtime")
    val runtimeDetail: Int?,
    @SerializedName("spoken_languages")
    val spokenLanguageDtosDetail: List<SpokenLanguageDto>?,
    @SerializedName("status")
    val statusDetail: String?,
    @SerializedName("tagline")
    val taglineDetail: String?,



    @SerializedName("created_by")
    val createdByDtoTvDetail: List<CreatedByDto>?,
    @SerializedName("episode_run_time")
    val episodeRunTimeTvDetail: List<Int>?,
    @SerializedName("in_production")
    val inProductionTvDetail: Boolean?,
    @SerializedName("languages")
    val languagesTvDetail: List<String>?,
    @SerializedName("last_air_date")
    val lastAirDateTvDetail: String?,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAirDtoTvDetail: LastEpisodeToAirDto?,
    @SerializedName("networks")
    val networksTvDetail: List<NetworkDto>?,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAirTvDetail: NextEpisodeToAirDto?,
    @SerializedName("number_of_episodes")
    val numberOfEpisodesTvDetail: Int?,
    @SerializedName("number_of_seasons")
    val numberOfSeasonsTvDetail: Int?,
    @SerializedName("seasons")
    val seasonsTvDetail: List<SeasonDto>?,
    @SerializedName("type")
    val typeTvDetail: String?,


    )