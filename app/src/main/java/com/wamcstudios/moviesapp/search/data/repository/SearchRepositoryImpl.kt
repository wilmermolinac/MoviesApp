package com.wamcstudios.moviesapp.search.data.repository

import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.core.common.CategoryMovies
import com.wamcstudios.moviesapp.core.common.MediaType
import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.core.utils.UiText
import com.wamcstudios.moviesapp.home.data.local.MediaDao
import com.wamcstudios.moviesapp.home.data.mapper.toMedia
import com.wamcstudios.moviesapp.home.data.remote.ApiService
import com.wamcstudios.moviesapp.home.data.remote.dto.MediaDto
import com.wamcstudios.moviesapp.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: MediaDao,
) : SearchRepository {

    override suspend fun getSearchList(
        query: String,
        page: Int,
        apiKey: String,
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
    ): Flow<Resource<List<Media>>> {
        return flow {
            emit(Resource.Loading())

            // Traemos la lista de objetos Genre en la base de datos y cada objeto Genre tiene un ID y un nombre
            // Primero, creamos un mapa de IDs de géneros a nombres de géneros para facilitar la búsqueda
            val genreIdToNameMap = dao.getAllGenres().associateBy({ it.id }, { it.name })

            val localMediaList = dao.getSearchList(query)

            val shouldJustLoadFromCache =
                localMediaList.isNotEmpty() && genreIdToNameMap.isNotEmpty() && !fetchFromRemote && !isRefresh


            if (shouldJustLoadFromCache) {
                emit(Resource.Success(localMediaList.map {
                    it.toMedia().copy(genres = it.genresIds.mapNotNull { genreId ->
                        // Luego, iteramos sobre remoteMediaList y reemplazamos cada ID de género
                        // con su nombre correspondiente
                        genreIdToNameMap[genreId]
                    } ?: emptyList())
                }))

                return@flow
            }


            val remoteMediaList = try {
                api.getSearchList(query, page, apiKey).mediaListDto

            } catch (e: IOException) {
                e.printStackTrace()
                emit(
                    Resource.Error(message = UiText.StringResource(R.string.msg_error_load_data),
                        data = localMediaList.map {
                            it.toMedia()
                        })
                )
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()

                emit(
                    Resource.Error(message = UiText.StringResource(R.string.msg_error_load_data),
                        data = localMediaList.map {
                            it.toMedia()
                        })
                )
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                val message = e.message ?: "Couldn't load data"

                emit(
                    Resource.Error(
                        message = UiText.DynamicString(message),
                        data = localMediaList.map {
                            it.toMedia()
                        })
                )
                return@flow
            }

            remoteMediaList?.let { mediaDtos: List<MediaDto> ->

                val mediaList = mediaDtos.map {
                    it.toMedia(
                        mediaType = it.mediaType ?: MediaType.Movie.name,
                        mediaCategory = CategoryMovies.TopRated.name
                    ).copy(genres = it.genreIds?.mapNotNull { genreId ->
                        // Luego, iteramos sobre remoteMediaList y reemplazamos cada ID de género
                        // con su nombre correspondiente
                        genreIdToNameMap[genreId]
                    } ?: emptyList())
                }

                emit(Resource.Success(mediaList))
            }
        }
    }
}