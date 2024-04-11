package com.wamcstudios.moviesapp.home.data.remote.dto


import com.google.gson.annotations.SerializedName

data class NetworkDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)