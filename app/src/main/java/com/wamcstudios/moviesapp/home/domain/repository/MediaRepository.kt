package com.wamcstudios.moviesapp.home.domain.repository

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.home.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    suspend fun getMoviesAndTvSeriesList(
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        mediaType: String,
        mediaCategory: String,
        page: Int,
        apiKey: String,
    ): Flow<Resource<List<Media>>>

    suspend fun getTrendingList(
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        trendingCategory: String,
        type: String,
        time: String,
        page: Int,
        apiKey: String,
    ): Flow<Resource<List<Media>>>

    suspend fun getMediaDetailById(
        mediaId: Int, mediaType: String,
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        apiKey: String,
    ): Flow<Resource<Media>>

    suspend fun updateMediaFavorite(mediaId: Int, isFavorite: Boolean)

    suspend fun getGenresList(isRefresh: Boolean): Flow<Resource<List<Genre>>>

}