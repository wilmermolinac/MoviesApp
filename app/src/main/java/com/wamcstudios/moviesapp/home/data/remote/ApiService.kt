package com.wamcstudios.moviesapp.home.data.remote

import com.wamcstudios.moviesapp.home.data.remote.dto.GenresDto
import com.wamcstudios.moviesapp.home.data.remote.dto.MediaDto
import com.wamcstudios.moviesapp.home.data.remote.dto.MediaListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{type}/{category}")
    suspend fun getMoviesAndTvSeriesList(
        @Path("type") type: String,
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ): MediaListDto


    @GET("trending/{type}/{time}")
    suspend fun getTrendingList(
        @Path("type") type: String,
        @Path("time") time: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ): MediaListDto

    @GET("genre/{type}/list")
    suspend fun getGenres(
        @Path("type") type: String,
        @Query("api_key") apiKey: String,
    ): GenresDto

    @GET("{type}/{mediaId}")
    suspend fun getMediaDetail(
        @Path("type") type: String,
        @Path("mediaId") mediaId: Int,
        @Query("api_key") apiKey: String,
    ): MediaDto

}