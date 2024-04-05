package com.wamcstudios.moviesapp.home.domain.repository

import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.core.domain.model.Media
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

    suspend fun getMediaDetailById(
        mediaId: Int
    ): Flow<Resource<Media>>

    suspend fun updateMediaFavorite(mediaId: Int, isFavorite: Boolean)

}