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
    @SerializedName("poster_path") val posterPath : String? = null,
    @SerializedName("backdrop_path") val backdropPath : String? = null,
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("release_date") val releaseDate: String? = "",

    //Movie has been liked
    var isFavorited: Boolean = false
) : Parcelable

