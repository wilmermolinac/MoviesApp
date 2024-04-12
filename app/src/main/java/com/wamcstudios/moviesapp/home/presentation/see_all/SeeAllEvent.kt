package com.wamcstudios.moviesapp.home.presentation.see_all

sealed class SeeAllEvent {

    object OnRefresh : SeeAllEvent()
    data class OnClickMediaItem(val mediaId: Int) : SeeAllEvent()

    object OnPaginate : SeeAllEvent()

    data class OnCLickTabItem(val mediaType: String, val selectedTabIndex: Int) : SeeAllEvent()
}