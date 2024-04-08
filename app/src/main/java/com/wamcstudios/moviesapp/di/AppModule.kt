package com.wamcstudios.moviesapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.wamcdevs.habitsjcapp.home.data.util.GsonParser
import com.wamcstudios.moviesapp.home.data.local.MediaDatabase
import com.wamcstudios.moviesapp.home.data.local.typeconverter.HomeTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMediaDatabase(@ApplicationContext context:Context): MediaDatabase {
        return Room.databaseBuilder(context, MediaDatabase::class.java, "moviesapp_db")
            .addTypeConverter(HomeTypeConverter(GsonParser(Gson()))).build()
    }
}