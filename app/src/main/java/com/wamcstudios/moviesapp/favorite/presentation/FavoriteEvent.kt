package com.wamcstudios.moviesapp.favorite.presentation

sealed class FavoriteEvent {

    data class OnMediaClick(val mediaId: Int, val mediaType: String):FavoriteEvent()


}