package com.wamcstudios.moviesapp.favorite.data.repository

import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.core.utils.UiText
import com.wamcstudios.moviesapp.favorite.domain.repository.FavoriteRepository
import com.wamcstudios.moviesapp.home.data.local.MediaDao
import com.wamcstudios.moviesapp.home.data.mapper.toMedia
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val dao: MediaDao) : FavoriteRepository {

    override suspend fun getAllMediaFavorite(): Flow<Resource<List<Media>>> {
        return flow {
            emit(Resource.Loading())

            val genreIdToNameMap = dao.getAllGenres().associateBy({ it.id }, { it.name })

            val localMediaList = dao.getAllMediaFavorite()

            if (localMediaList.isEmpty()) {
                emit(Resource.Error(message = UiText.StringResource(R.string.msg_empty_data_favorite)))
                return@flow
            }

            emit(Resource.Success(data = localMediaList.map {
                it.toMedia().copy(genres = it.genresIds.mapNotNull {
                    genreIdToNameMap[it]
                })
            }))



        }
    }
}