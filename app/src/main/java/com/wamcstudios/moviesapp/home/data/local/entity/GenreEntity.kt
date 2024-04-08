package com.wamcstudios.moviesapp.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_genre")
data class GenreEntity(@PrimaryKey val id: Int, val name: String)
