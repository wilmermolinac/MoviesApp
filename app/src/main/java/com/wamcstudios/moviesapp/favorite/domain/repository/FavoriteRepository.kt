package com.wamcstudios.moviesapp.favorite.domain.repository

import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun  getAllMediaFavorite():Flow<Resource<List<Media>>>
}