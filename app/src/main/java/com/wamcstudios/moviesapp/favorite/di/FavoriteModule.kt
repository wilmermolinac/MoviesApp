package com.wamcstudios.moviesapp.favorite.di

import com.wamcstudios.moviesapp.favorite.data.repository.FavoriteRepositoryImpl
import com.wamcstudios.moviesapp.favorite.domain.repository.FavoriteRepository
import com.wamcstudios.moviesapp.favorite.domain.use_case.FavoriteUseCases
import com.wamcstudios.moviesapp.favorite.domain.use_case.GetAllMediaFavorite
import com.wamcstudios.moviesapp.home.data.local.MediaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {

    @Provides
    @Singleton
    fun provideFavoriteRepository(db: MediaDatabase): FavoriteRepository {
        return FavoriteRepositoryImpl(dao = db.mediaDao)
    }

    @Provides
    @Singleton
    fun providesFavoritesUseCases(repository: FavoriteRepository): FavoriteUseCases {
        return FavoriteUseCases(getAllMediaFavorite = GetAllMediaFavorite(repository))
    }

}