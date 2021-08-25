package com.g.vicprojetointegrador.data.model

import com.google.gson.annotations.SerializedName


data class Crew(
    val id: Int = 0,
    @SerializedName("name") val name: String,
    @SerializedName("job") val job: String,
    @SerializedName("profile_path") val profilePath: String,
)

