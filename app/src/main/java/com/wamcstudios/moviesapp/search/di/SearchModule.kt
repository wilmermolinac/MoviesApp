package com.wamcstudios.moviesapp.search.di

import com.wamcstudios.moviesapp.home.data.local.MediaDatabase
import com.wamcstudios.moviesapp.home.data.remote.ApiService
import com.wamcstudios.moviesapp.search.data.repository.SearchRepositoryImpl
import com.wamcstudios.moviesapp.search.domain.repository.SearchRepository
import com.wamcstudios.moviesapp.search.domain.use_case.GetSearchList
import com.wamcstudios.moviesapp.search.domain.use_case.SearchUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchRepository(apiService: ApiService, db: MediaDatabase): SearchRepository {
        return SearchRepositoryImpl(api = apiService, dao = db.mediaDao)
    }

    @Provides
    @Singleton
    fun provideSearchUseCases(repository: SearchRepository): SearchUseCases {
        return SearchUseCases(getSearchList = GetSearchList(repository))
    }
}