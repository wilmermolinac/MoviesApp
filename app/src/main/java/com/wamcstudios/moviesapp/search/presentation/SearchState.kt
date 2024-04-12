package com.wamcstudios.moviesapp.search.presentation

import com.wamcstudios.moviesapp.core.domain.model.Media

data class SearchState(
    val mediaList: List<Media> = emptyList(),
    val query: String = "", val page: Int = 1,
    val isLoading: Boolean = false, val isConnect: Boolean = false, val isRefresh: Boolean = false,
)
