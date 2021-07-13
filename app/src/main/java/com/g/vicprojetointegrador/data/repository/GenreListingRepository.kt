package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.Genre

class GenreListingRepository {
    private val allGenres = listOf(
        Genre(1, "Ação"),
        Genre(2, "Anime"),
        Genre(3, "Biografia"),
        Genre(4, "Comédia"),
        Genre(5, "Drama"),
    )
}