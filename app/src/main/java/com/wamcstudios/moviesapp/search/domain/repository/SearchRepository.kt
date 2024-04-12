package com.wamcstudios.moviesapp.search.domain.repository

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun getSearchList(
        query: String, page: Int,
        apiKey: String,
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
    ): Flow<Resource<List<Media>>>
}