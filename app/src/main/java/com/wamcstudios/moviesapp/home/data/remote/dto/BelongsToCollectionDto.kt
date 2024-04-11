package com.wamcstudios.moviesapp.home.data.remote.dto


import com.google.gson.annotations.SerializedName

data class BelongsToCollectionDto(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)