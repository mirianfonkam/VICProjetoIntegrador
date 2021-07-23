package com.g.vicprojetointegrador.data.model

import com.google.gson.annotations.SerializedName

class ReleaseDatesResponse(
    @SerializedName("release_dates") val releaseDates: List<ReleaseDate>
)