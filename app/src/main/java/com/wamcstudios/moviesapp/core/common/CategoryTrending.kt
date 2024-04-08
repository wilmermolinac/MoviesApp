package com.wamcstudios.moviesapp.core.common

sealed class CategoryTrending(val name: String) {

    object Trending : CategoryTrending(name = "trending")

    companion object {
        fun fromString(name: String): CategoryTrending {
            return when (name) {
                "trending" -> Trending
                else -> Trending
            }
        }
    }
}