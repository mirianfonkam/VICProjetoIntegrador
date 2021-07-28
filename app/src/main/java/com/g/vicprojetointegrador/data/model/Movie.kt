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
    @SerializedName("title") val title : String = "", //column SQL snake case
    @SerializedName("overview") val overview: String = "",
    @SerializedName("poster_path") val posterPath : String = "",
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("release_date") val releaseDate: String = "",
    @SerializedName("runtime") val runtimeMinutes: Int = 0,
    @SerializedName("genre_ids") val genreIds: List<Int> = emptyList(),

    //Movie has been liked
    var isFavorited: Boolean = false

) : Parcelable

//MaturityRating (certification) is in releaseDate https://developers.themoviedb.org/3/movies/get-movie-release-dates