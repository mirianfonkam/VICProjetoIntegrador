package com.g.vicprojetointegrador.data.model

import com.google.gson.annotations.SerializedName

class ReleaseInfo(
    @SerializedName("iso_3166_1") val countryCode: String,
    @SerializedName("release_dates") val releaseDates: List<ReleaseDate>
)