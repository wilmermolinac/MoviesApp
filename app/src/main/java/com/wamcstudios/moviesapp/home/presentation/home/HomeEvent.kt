package com.wamcstudios.moviesapp.home.presentation.home

sealed class HomeEvent {

    object OnPullRefresh : HomeEvent()
}