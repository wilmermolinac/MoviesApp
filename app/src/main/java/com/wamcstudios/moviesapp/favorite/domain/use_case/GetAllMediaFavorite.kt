package com.wamcstudios.moviesapp.favorite.domain.use_case

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.favorite.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMediaFavorite @Inject constructor(private val repository: FavoriteRepository) {
    suspend operator fun invoke():Flow<Resource<List<Media>>>{
        return repository.getAllMediaFavorite()
    }
}