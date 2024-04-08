package com.wamcstudios.moviesapp.home.domain.use_case

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.home.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingList @Inject constructor(private val repository: MediaRepository) {
    suspend operator fun invoke(
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        trendingCategory: String,
        type: String,
        time: String,
        page: Int,
        apiKey: String,
    ): Flow<Resource<List<Media>>> {
        return repository.getTrendingList(
            fetchFromRemote,
            isRefresh,
            trendingCategory,
            type,
            time,
            page,
            apiKey
        )
    }
}