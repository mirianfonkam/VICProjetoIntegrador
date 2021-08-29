package com.g.vicprojetointegrador.data.model

import com.g.vicprojetointegrador.utils.TMDBConstants
import com.google.gson.annotations.SerializedName


data class Crew(
    val id: Int = 0,
    @SerializedName("name") val name: String,
    @SerializedName("job") val job: String,
    @SerializedName("profile_path") val profilePath: String? = null,
) {
    val profileImageUrl : String?
        get() {
            profilePath?.let {return "${TMDBConstants.IMAGE_URL}${it}"}
            return profilePath
        }
}


//TODO: Map Crew & Cast to People Entity

