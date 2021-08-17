package com.g.vicprojetointegrador.data.model

import com.google.gson.annotations.SerializedName

class MovieDetails (
    val id: Int,
    @SerializedName("title") val title : String = "",
    @SerializedName("overview") val overview: String = "",
    @SerializedName("poster_path") val posterPath : String? = null,
    @SerializedName("backdrop_path") val backdropPath : String? = null,
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("release_date") val releaseDate: String = "",
    @SerializedName("release_dates") val releaseInfoResponse: ReleaseInfoResponse,
    @SerializedName("runtime") val runtime: Int = 0,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("credits") val credits: Credits,


)

