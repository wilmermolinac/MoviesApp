package com.wamcstudios.moviesapp.home.presentation.detail

sealed class DetailEvent {

    object OnRefresh : DetailEvent()

    object OnCLickFavorite : DetailEvent()
}