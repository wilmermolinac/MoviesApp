package com.wamcstudios.moviesapp.home.domain.use_case

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.home.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMediaDetailById @Inject constructor(private val repository: MediaRepository) {

    suspend operator fun invoke(
        mediaId: Int,
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        apiKey: String,
    ): Flow<Resource<Media>> {
        return repository.getMediaDetailById(
            mediaId,
            fetchFromRemote,
            isRefresh,
            apiKey,
        )
    }
}