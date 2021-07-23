package com.g.vicprojetointegrador.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Cast(
    val id: Int = 0,
    @SerializedName("character") val characterName: String = "",
    @SerializedName("name") val actorName: String = "",
    @SerializedName("profile_path") val profilePath: String = "",
) : Parcelable

@Parcelize
data class Crew(
    val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("job") val job: String = "",
    @SerializedName("profile_path") val profilePath: String = "",
) : Parcelable

