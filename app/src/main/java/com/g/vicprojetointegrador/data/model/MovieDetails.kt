package com.g.vicprojetointegrador.data.model

import com.google.gson.annotations.SerializedName

class MovieDetails (
    val id: Int,
    @SerializedName("release_dates") val releaseInfoResponse: ReleaseInfoResponse,
    @SerializedName("runtime") val runtime: Int = 0,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("credits") val credits: Credits,
)

