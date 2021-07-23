package com.g.vicprojetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class ReleaseDate(
    @SerializedName("certification") val maturityRating: String,
    @SerializedName("release_date") val releaseDate: String,
)