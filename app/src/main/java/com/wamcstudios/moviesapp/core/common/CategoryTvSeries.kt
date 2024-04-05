package com.wamcstudios.moviesapp.core.common

sealed class CategoryTvSeries(val name: String) {

    object Popular : CategoryTvSeries(name = "popular")
    object TopRated : CategoryTvSeries(name = "top_rated")
    object AiringToday : CategoryTvSeries(name = "airing_today")
    object OnTheAir : CategoryTvSeries(name = "on_the_air")

    companion object {
        fun fromString(name: String): CategoryTvSeries {
            return when (name) {
                "popular" -> Popular
                "top_rated" -> TopRated
                "airing_today" -> AiringToday
                "on_the_air" -> OnTheAir
                else -> Popular
            }
        }
    }

}