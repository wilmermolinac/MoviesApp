package com.wamcstudios.moviesapp.home.di

import androidx.room.Database
import com.google.gson.GsonBuilder
import com.wamcstudios.moviesapp.core.common.Constants
import com.wamcstudios.moviesapp.home.data.local.MediaDatabase
import com.wamcstudios.moviesapp.home.data.remote.ApiService
import com.wamcstudios.moviesapp.home.data.repository.MediaRepositoryImpl
import com.wamcstudios.moviesapp.home.domain.repository.MediaRepository
import com.wamcstudios.moviesapp.home.domain.use_case.GetGenresList
import com.wamcstudios.moviesapp.home.domain.use_case.GetMediaDetailById
import com.wamcstudios.moviesapp.home.domain.use_case.GetMoviesAndTvSeriesList
import com.wamcstudios.moviesapp.home.domain.use_case.GetTrendingList
import com.wamcstudios.moviesapp.home.domain.use_case.HomeUseCases
import com.wamcstudios.moviesapp.home.domain.use_case.UpdateMediaFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): ApiService {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMediaRepository(db: MediaDatabase, api: ApiService): MediaRepository {
        return MediaRepositoryImpl(dao = db.mediaDao, api = api)
    }

    @Provides
    @Singleton
    fun provideHomeUseCases(repository: MediaRepository): HomeUseCases {
        return HomeUseCases(
            getMediaDetailById = GetMediaDetailById(repository),
            getMoviesAndTvSeriesList = GetMoviesAndTvSeriesList(repository),
            updateMediaFavorite = UpdateMediaFavorite(repository),
            getTrendingList = GetTrendingList(repository), getGenresList = GetGenresList(repository)
        )
    }


}