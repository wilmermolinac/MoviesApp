package com.wamcstudios.moviesapp.favorite.presentation

import com.wamcstudios.moviesapp.core.domain.model.Media

data class FavoriteState(val mediaList: List<Media> = emptyList(), val isLoading: Boolean = false)
