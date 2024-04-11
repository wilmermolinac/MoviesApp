package com.wamcstudios.moviesapp.home.data.repository

import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.core.common.Constants
import com.wamcstudios.moviesapp.core.common.MediaType
import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.core.utils.UiText
import com.wamcstudios.moviesapp.home.data.local.MediaDao
import com.wamcstudios.moviesapp.home.data.mapper.toGenre
import com.wamcstudios.moviesapp.home.data.mapper.toGenreEntity
import com.wamcstudios.moviesapp.home.data.mapper.toMedia
import com.wamcstudios.moviesapp.home.data.mapper.toMediaEntity
import com.wamcstudios.moviesapp.home.data.remote.ApiService
import com.wamcstudios.moviesapp.home.data.remote.dto.GenreDto
import com.wamcstudios.moviesapp.home.domain.model.Genre
import com.wamcstudios.moviesapp.home.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val dao: MediaDao,
    private val api: ApiService,
) : MediaRepository {

    override suspend fun getMoviesAndTvSeriesList(
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        mediaType: String,
        mediaCategory: String,
        page: Int,
        apiKey: String,
    ): Flow<Resource<List<Media>>> {
        return flow {


            // Traemos la lista de objetos Genre en la base de datos y cada objeto Genre tiene un ID y un nombre
            // Primero, creamos un mapa de IDs de géneros a nombres de géneros para facilitar la búsqueda
            val genreIdToNameMap = dao.getAllGenres().associateBy({ it.id }, { it.name })

            val localMediaList =
                dao.getMediaListByTypeAndCategory(mediaType = mediaType, category = mediaCategory)

            emit(Resource.Loading())

            val shouldJustLoadFromCache =
                localMediaList.isNotEmpty() && genreIdToNameMap.isNotEmpty() && !fetchFromRemote && !isRefresh

            if (shouldJustLoadFromCache) {
                emit(Resource.Success(localMediaList.map {
                    it.toMedia()/*.copy(genreIds = it.genreIds.mapNotNull { genreId ->
                        // Luego, iteramos sobre remoteMediaList y reemplazamos cada ID de género
                        // con su nombre correspondiente
                        genreIdToNameMap[genreId.toInt()]
                    } ?: emptyList())*/
                }))
                return@flow
            }

            var searchPage = page
            if (isRefresh) {
                dao.deleteMediaByTypeAndCategory(mediaType, mediaCategory, isFavorite = false)
                searchPage = 1
            }

            val remoteMediaList = try {
                api.getMoviesAndTvSeriesList(
                    type = mediaType, category = mediaCategory, page = searchPage, apiKey = apiKey
                ).mediaListDto

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
                    Resource.Error(message = UiText.DynamicString(message),
                        data = localMediaList.map {
                            it.toMedia()
                        })
                )
                return@flow
            }

            remoteMediaList.let { mediaDtos ->
                /*val media = mediaDtos.map {
                    it.toMedia(mediaCategory, mediaType)
                        .copy(genres = it.genreIds?.mapNotNull { genreId ->
                            // Luego, iteramos sobre remoteMediaList y reemplazamos cada ID de género
                            // con su nombre correspondiente
                            genreIdToNameMap[genreId]
                        } ?: emptyList())
                }*/

                /*val mediaEntity = mediaDtos.map {
                    it.toMediaEntity(mediaCategory, mediaType).copy(genres =
                    it.genreIds?.mapNotNull { genreId ->
                        // Luego, iteramos sobre remoteMediaList y reemplazamos cada ID de género
                        // con su nombre correspondiente
                        genreIdToNameMap[genreId]
                    } ?: emptyList())
                }*/

                val combinelist = mediaDtos.map { mediaDto ->

                    // Mapeamos cada objeto 'mediaDto' a un objeto 'mediaEntityRemote'.
                    val mediaEntityRemote = mediaDto.toMediaEntity(mediaCategory, mediaType)

                    // Buscamos si el objeto 'mediaEntityRemote' ya existe en la lista local 'localMediaList'.
                    localMediaList.find {
                        it.id == mediaEntityRemote.id
                    }?.let {
                        // Si el objeto ya existe, creamos una copia del objeto pero mantenemos
                        // el valor original de 'isFavorite' de la lista local.
                        it.copy(isFavorite = it.isFavorite)
                    }
                        ?: mediaEntityRemote // Si el objeto no existe, usamos el objeto 'mediaEntityRemote'.
                }


                dao.upsertMediaList(combinelist)

                val media = combinelist.map {
                    it.toMedia().copy(genres = it.genresIds.mapNotNull { genreId ->
                        // Para cada ID de género en 'mediaDto', buscamos su nombre correspondiente
                        // en el mapa 'genreIdToNameMap' y lo agregamos a la lista de géneros.
                        genreIdToNameMap[genreId]
                    } ?: emptyList())
                }

                emit(Resource.Success(media))

            }


        }
    }


    override suspend fun getTrendingList(
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        trendingCategory: String,
        type: String,
        time: String,
        page: Int,
        apiKey: String,
    ): Flow<Resource<List<Media>>> {
        return flow {
            emit(Resource.Loading())

            val genreIdToNameMap = dao.getAllGenres().associateBy({ it.id }, { it.name })

            val localMediaList = dao.getTrendingMediaList(trendingCategory)

            val shouldJustLoadFromCache =
                localMediaList.isNotEmpty() && !fetchFromRemote && !isRefresh

            if (shouldJustLoadFromCache) {
                emit(Resource.Success(data = localMediaList.map {
                    it.toMedia()
                }))

                return@flow
            }

            var searchPage = page
            if (isRefresh) {
                dao.deleteTrendingMedia(category = trendingCategory, isFavorite = false)
                searchPage = 1
            }

            val remoteTrendingMediaList = try {
                api.getTrendingList(
                    type = type, time = time, page = searchPage, apiKey = apiKey
                ).mediaListDto
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
                    Resource.Error(message = UiText.DynamicString(message),
                        data = localMediaList.map {
                            it.toMedia()
                        })
                )
                return@flow
            }

            remoteTrendingMediaList.let { mediaListDto ->
                /*val media = mediaListDto.map {
                    it.toMedia(mediaType = type, mediaCategory = trendingCategory)
                        .copy(genres = it.genreIds?.mapNotNull { genreId ->
                            // Luego, iteramos sobre remoteMediaList y reemplazamos cada ID de género
                            // con su nombre correspondiente
                            genreIdToNameMap[genreId]
                        } ?: emptyList())
                }

                val mediaEntities = mediaListDto.map {
                    it.toMediaEntity(mediaCategory = trendingCategory, mediaType = type)
                        .copy(genres = it.genreIds?.mapNotNull { genreId ->
                            genreIdToNameMap[genreId]
                        } ?: emptyList())
                }*/

                val combinelist = mediaListDto.map { mediaDto ->

                    // Mapeamos cada objeto 'mediaDto' a un objeto 'mediaEntityRemote'.
                    val mediaEntityRemote =
                        mediaDto.toMediaEntity(mediaCategory = trendingCategory, mediaType = type)

                    // Buscamos si el objeto 'mediaEntityRemote' ya existe en la lista local 'localMediaList'.
                    localMediaList.find { localMediaEntity ->
                        localMediaEntity.id == mediaEntityRemote.id
                    }?.let {
                        // Si el objeto ya existe, creamos una copia del objeto pero mantenemos
                        // el valor original de 'isFavorite' de la lista local.
                        it.copy(isFavorite = it.isFavorite)
                    }
                        ?: mediaEntityRemote // Si el objeto no existe, usamos el objeto 'mediaEntityRemote'.
                }


                dao.upsertMediaList(combinelist)

                val media = combinelist.map {
                    it.toMedia().copy(genres = it.genresIds.mapNotNull { genreId ->
                        // Para cada ID de género en 'mediaDto', buscamos su nombre correspondiente
                        // en el mapa 'genreIdToNameMap' y lo agregamos a la lista de géneros.
                        genreIdToNameMap[genreId]
                    } ?: emptyList())
                }

                emit(Resource.Success(media))


            }


        }
    }

    override suspend fun getMediaDetailById(
        mediaId: Int,
        fetchFromRemote: Boolean,
        isRefresh: Boolean,
        apiKey: String,
    ): Flow<Resource<Media>> {
        return flow {
            emit(Resource.Loading())
            val localMediaItem = dao.getMediaById(mediaId)

            val shouldJustLoadFromCache = !isRefresh && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(
                    Resource.Success(
                        data = localMediaItem.toMedia()
                    )
                )
                return@flow
            }

            val remoteMediaItem = try {
                api.getMediaDetail(
                    type = localMediaItem.mediaType,
                    mediaId = mediaId,
                    apiKey = apiKey
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(
                    Resource.Error(message = UiText.StringResource(R.string.msg_error_load_data))
                )
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()

                emit(
                    Resource.Error(message = UiText.StringResource(R.string.msg_error_load_data))
                )
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                val message = e.message ?: "Couldn't load data"

                emit(
                    Resource.Error(message = UiText.DynamicString(message))
                )
                return@flow
            }


            remoteMediaItem.let { mediaDto ->

                val mediaEntity = mediaDto.toMediaEntity(
                    mediaCategory = localMediaItem.mediaCategory,
                    mediaType = localMediaItem.mediaType
                ).copy(
                    adult = localMediaItem.adult,
                    backdropPath = localMediaItem.backdropPath,
                    genresIds = localMediaItem.genresIds,
                    originalLanguage = localMediaItem.originalLanguage,
                    originalTitle = localMediaItem.originalTitle,
                    overview = localMediaItem.overview,
                    popularity = localMediaItem.popularity,
                    posterPath = localMediaItem.posterPath,
                    releaseDate = localMediaItem.releaseDate,
                    title = localMediaItem.title,
                    voteAverage = localMediaItem.voteAverage,
                    voteCount = localMediaItem.voteCount,
                    isFavorite = localMediaItem.isFavorite
                )
                dao.updateMediaItem(mediaEntity)
                emit(Resource.Success(mediaEntity.toMedia()))
            }


        }
    }


    override suspend fun updateMediaFavorite(mediaId: Int, isFavorite: Boolean) {
        dao.updateMediaFavorite(mediaId, isFavorite)
    }

    override suspend fun getGenresList(isRefresh: Boolean): Flow<Resource<List<Genre>>> {
        return flow {

            emit(Resource.Loading())

            val genresList = dao.getAllGenres()
            if (genresList.isNotEmpty() && !isRefresh) {
                emit(Resource.Success(genresList.map {
                    it.toGenre()
                }))

                return@flow
            }

            val remoteGenresList = try {

                val movieGenres =
                    api.getGenres(type = MediaType.Movie.name, apiKey = Constants.API_KEY).genreDtos
                val tvSeriesGenres = api.getGenres(
                    type = MediaType.TvSeries.name,
                    apiKey = Constants.API_KEY
                ).genreDtos

                // Unimos las dos listas y eliminamos los duplicados
                (movieGenres + tvSeriesGenres).distinctBy { it.id }

            } catch (e: IOException) {
                e.printStackTrace()
                emit(
                    Resource.Error(message = UiText.StringResource(R.string.msg_error_load_data))
                )
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()

                emit(
                    Resource.Error(message = UiText.StringResource(R.string.msg_error_load_data))
                )
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                val message = e.message ?: "Couldn't load data"

                emit(
                    Resource.Error(message = UiText.DynamicString(message))
                )
                return@flow
            }

            remoteGenresList.let { genreDtos: List<GenreDto> ->

                val genres = genreDtos.map {
                    it.toGenre()
                }

                val genreEntities = genres.map {
                    it.toGenreEntity()
                }

                dao.upsertGenres(genreEntities)

                emit(Resource.Success(genres))
            }


        }
    }
}