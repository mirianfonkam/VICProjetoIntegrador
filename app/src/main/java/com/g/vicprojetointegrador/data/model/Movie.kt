package com.g.vicprojetointegrador.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int,
    @SerializedName("title") val title : String = "",
    @SerializedName("overview") val overview: String = "",
    @SerializedName("poster_path") val posterPath : String = "",
    @SerializedName("backdrop_path") val backdropPath : String = "",
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("release_date") val releaseDate: String = "",
) : Parcelable

//MaturityRating (certification) is in releaseDate https://developers.themoviedb.org/3/movies/get-movie-release-dates