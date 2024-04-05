package com.wamcstudios.moviesapp.core.common

sealed class MediaType(val name: String) {
    object Movie : MediaType(name = "movie")
    object TvSeries : MediaType(name = "tv")

    companion object {
        fun fromString(name: String): MediaType {
            return when (name) {
                "movie" -> Movie
                "tv" -> TvSeries
                else -> Movie
            }
        }
    }
}