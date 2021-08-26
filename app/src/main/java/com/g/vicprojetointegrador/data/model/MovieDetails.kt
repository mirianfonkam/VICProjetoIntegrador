package com.g.vicprojetointegrador.data.model

import com.g.vicprojetointegrador.utils.formatHourMinutes
import com.google.gson.annotations.SerializedName

class MovieDetails (
    val id: Int,
    @SerializedName("release_dates") val releaseInfoResponse: ReleaseInfoResponse,
    @SerializedName("runtime") val runtime: Int = 0,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("credits") val credits: Credits,
) {
    val duration : String
        get() {
            return runtime.formatHourMinutes()
        }

    val certification : String?
        get(){
            return releaseInfoResponse.results.filter { releaseInfo ->
                releaseInfo.countryCode == "US"}.flatMap { it.releaseDates }.filter { releaseDate ->
                    releaseDate.maturityRating.isNotEmpty()
                }.firstNotNullOfOrNull { it.maturityRating }
        }

    val director : Crew?
        get() {
            return credits.crew.firstOrNull { crew -> crew.job == "Director" }
        }

    val actors : List<Cast>
        get(){
            return credits.cast
        }
}
