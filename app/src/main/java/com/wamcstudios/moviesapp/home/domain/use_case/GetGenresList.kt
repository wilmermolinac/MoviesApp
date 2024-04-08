package com.wamcstudios.moviesapp.home.domain.use_case

import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.home.domain.model.Genre
import com.wamcstudios.moviesapp.home.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresList @Inject constructor(private val repository: MediaRepository) {

    suspend operator fun invoke(isRefresh: Boolean): Flow<Resource<List<Genre>>> {
        return repository.getGenresList(isRefresh)
    }
}