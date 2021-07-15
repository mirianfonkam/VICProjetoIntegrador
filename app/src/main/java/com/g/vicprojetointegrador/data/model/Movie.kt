package com.g.vicprojetointegrador.data.model

data class Movie(
    val id: Int,
    val title : String,
    val posterPath : String = "",
    val voteAverage: Int = 0,
    val maturityRating: String = "",
    val releaseDate: String = "",
    val duration: String = "",
    val overview: String = "",
    val genreIds: List<Int> = emptyList(),
    val castIds: List<Int> = emptyList()
)