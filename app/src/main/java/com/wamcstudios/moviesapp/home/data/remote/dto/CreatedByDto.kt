package com.wamcstudios.moviesapp.home.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CreatedByDto(
    @SerializedName("credit_id")
    val creditId: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_path")
    val profilePath: String?
)