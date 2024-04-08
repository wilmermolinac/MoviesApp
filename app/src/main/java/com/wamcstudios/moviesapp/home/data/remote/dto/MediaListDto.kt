package com.wamcstudios.moviesapp.home.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MediaListDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val mediaListDto: List<MediaDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)