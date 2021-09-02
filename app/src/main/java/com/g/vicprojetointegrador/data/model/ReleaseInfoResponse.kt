package com.g.vicprojetointegrador.data.model

import com.google.gson.annotations.SerializedName

data class ReleaseInfoResponse(
    @SerializedName("results") val results: List<ReleaseInfo>,
)