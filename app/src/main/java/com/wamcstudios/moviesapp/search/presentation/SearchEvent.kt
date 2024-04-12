package com.wamcstudios.moviesapp.search.presentation

sealed class SearchEvent {

    data class ChangeQuery(val query: String) : SearchEvent()

    data class OnClickMedia(val mediaId: Int, val mediaType: String) : SearchEvent()

    object OnRefresh : SearchEvent()

    object OnPaginate : SearchEvent()
}