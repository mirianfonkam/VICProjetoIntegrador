package com.g.vicprojetointegrador.data.model

import com.google.gson.annotations.SerializedName

class ReleaseInfoResponse(
    @SerializedName("results") val results: List<ReleaseInfo>,
)