package com.wamcstudios.moviesapp.home.presentation.detail

import com.wamcstudios.moviesapp.core.domain.model.Media

data class DetailState(
    val mediaItem: Media? = null,
    val isLoading: Boolean = false,
    val isRefresh: Boolean = false,
    val mediaId: Int = 0,
    val isConnected: Boolean = false,
    val isFavorite: Boolean = false,
)
