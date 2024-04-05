package com.wamcstudios.moviesapp.home.domain.use_case

import androidx.room.Insert
import com.wamcstudios.moviesapp.home.domain.repository.MediaRepository
import javax.inject.Inject

class UpdateMediaFavorite @Inject constructor(private  val repository: MediaRepository) {

    suspend operator fun invoke(mediaId: Int, isFavorite: Boolean){
        repository.updateMediaFavorite(mediaId, isFavorite)
    }
}