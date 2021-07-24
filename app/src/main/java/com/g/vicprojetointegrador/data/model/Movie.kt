package com.g.vicprojetointegrador.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int = 0,
    @SerializedName("title") val title : String = "",
    @SerializedName("overview") val overview: String = "",
    @SerializedName("poster_path") val posterPath : String = "",
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("release_date") val releaseDate: String = "",
    @SerializedName("runtime") val runtimeMinutes: Int = 0,
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList(),

    //Movie has a been liked
    var isFavorite: Boolean = false

) : Parcelable

//MaturityRating (certification) is in releaseDate https://developers.themoviedb.org/3/movies/get-movie-release-dates