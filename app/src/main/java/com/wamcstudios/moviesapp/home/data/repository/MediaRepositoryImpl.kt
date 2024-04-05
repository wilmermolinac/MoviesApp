package com.wamcstudios.moviesapp.home.data.repository

import com.wamcstudios.moviesapp.R
import com.wamcstudios.moviesapp.core.domain.model.Media
import com.wamcstudios.moviesapp.core.utils.Resource
import com.wamcstudios.moviesapp.core.utils.UiText
import com.wamcstudios.moviesapp.home.data.local.MediaDao
import com.wamcstudios.moviesapp.home.data.mapper.toMedia
import com.wamcstudios.moviesapp.home.data.mapper.toMediaEntity
import com.wamcstudios.moviesapp.home.data.remote.ApiService
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

            val localMediaList =
                dao.getMediaListByTypeAndCategory(mediaType = mediaType, category = mediaCategory)

            emit(Resource.Loading())

            val shouldJustLoadFromCache =
                localMediaList.isNotEmpty() && !fetchFromRemote && !isRefresh

            if (shouldJustLoadFromCache) {
                emit(Resource.Success(localMediaList.map {
                    it.toMedia(mediaType = mediaType, mediaCategory = mediaCategory)
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
                    type = mediaType,
                    category = mediaCategory,
                    page = searchPage,
                    apiKey = apiKey
                ).mediaDtos
            } catch (e: IOException) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        message = UiText.StringResource(R.string.msg_error_load_data),
                        data = localMediaList.map {
                            it.toMedia(mediaType, mediaCategory = mediaCategory)
                        })
                )
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()

                emit(
                    Resource.Error(
                        message = UiText.StringResource(R.string.msg_error_load_data),
                        data = localMediaList.map {
                            it.toMedia(mediaType, mediaCategory = mediaCategory)
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
                            it.toMedia(mediaType, mediaCategory = mediaCategory)
                        })
                )
                return@flow
            }

            remoteMediaList.let { mediaDtos ->
                val media = mediaDtos.map {
                    it.toMedia(mediaCategory, mediaType)
                }

                val entities = mediaDtos.map {
                    it.toMediaEntity(mediaCategory, mediaType)
                }

                dao.upsertMediaList(entities)

                emit(Resource.Success(media))

            }


        }
    }

    override suspend fun getMediaDetailById(
        mediaId: Int,
    ): Flow<Resource<Media>> {

        return flow {
            emit(Resource.Loading())
            val mediaItem = dao.getMediaById(mediaId)

            if (mediaItem != null) {
                emit(
                    Resource.Success(
                        data = mediaItem.toMedia(
                            mediaItem.mediaType,
                            mediaItem.mediaCategory
                        )
                    )
                )
                return@flow
            }


            emit(Resource.Error(message = UiText.StringResource(R.string.msg_error_load_media)))


        }

    }

    override suspend fun updateMediaFavorite(mediaId: Int, isFavorite: Boolean) {
        dao.updateMediaFavorite(mediaId, isFavorite)
    }
}