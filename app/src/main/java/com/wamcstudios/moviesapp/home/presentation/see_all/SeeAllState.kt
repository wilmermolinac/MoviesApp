package com.wamcstudios.moviesapp.home.presentation.see_all

import com.wamcstudios.moviesapp.core.common.CategoryMovies
import com.wamcstudios.moviesapp.core.common.MediaType
import com.wamcstudios.moviesapp.core.domain.model.Media

data class SeeAllState(
    val category: String = CategoryMovies.Popular.name,
    val type: String = MediaType.Movie.name,
    val mediaList: List<Media> = emptyList(),
    val page: Int = 1,
    val isRefresh: Boolean = false,
    val isConnected: Boolean = false,
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val tabs: List<String> = tabsList,
) {
    companion object {
        private val tabsList = listOf(MediaType.Movie.name, MediaType.TvSeries.name)
    }

}
