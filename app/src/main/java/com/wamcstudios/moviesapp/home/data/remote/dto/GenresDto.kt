package com.wamcstudios.moviesapp.home.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GenresDto(
    @SerializedName("genres")
    val genreDtos: List<GenreDto>
)