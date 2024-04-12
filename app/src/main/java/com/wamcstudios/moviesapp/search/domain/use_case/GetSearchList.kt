package com.wamcstudios.moviesapp.search.domain.use_case

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchList @Inject constructor(private val repository: SearchRepository) {

    suspend operator fun invoke(
        query: String, page: Int,
        apiKey: String,
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
    ): Flow<Resource<List<Media>>> {
        return repository.getSearchList(query, page, apiKey, fetchFromRemote, isRefresh)
    }
}