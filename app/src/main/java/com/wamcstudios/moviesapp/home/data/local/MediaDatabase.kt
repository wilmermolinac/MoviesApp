package com.wamcstudios.moviesapp.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wamcstudios.moviesapp.home.data.local.typeconverter.HomeTypeConverter

@Database(entities = [MediaDatabase::class], version = 1)
@TypeConverters(HomeTypeConverter::class)
abstract class MediaDatabase : RoomDatabase() {
    abstract val mediaDao: MediaDao
}