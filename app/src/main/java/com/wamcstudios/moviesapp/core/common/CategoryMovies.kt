package com.wamcstudios.moviesapp.core.common

sealed class CategoryMovies(val name: String) {

    object Popular : CategoryMovies(name = "popular")
    object Upcoming : CategoryMovies(name = "upcoming")

    object TopRated : CategoryMovies(name = "top_rated")

    object NowPlaying : CategoryMovies(name = "now_playing")

    companion object {
        fun fromString(name: String): CategoryMovies {
            return when (name) {
                "popular" -> Popular
                "upcoming" -> Upcoming
                "top_rated" -> TopRated
                "now_playing" -> NowPlaying
                else -> Popular
            }
        }

    }


}