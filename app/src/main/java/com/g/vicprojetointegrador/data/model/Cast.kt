package com.g.vicprojetointegrador.data.model

import com.google.gson.annotations.SerializedName


data class Cast(
    val id: Int = 0,
    @SerializedName("character") val characterName: String,
    @SerializedName("name") val actorName: String,
    @SerializedName("profile_path") val profilePath: String,
)