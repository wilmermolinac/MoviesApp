package com.wamcstudios.moviesapp.home.presentation.home

sealed class HomeEvent {

    object OnPullRefresh : HomeEvent()
    data class OnMediaClick(val mediaId: Int) : HomeEvent()

    data class OnClickSeeAll(val category: String) : HomeEvent()
}