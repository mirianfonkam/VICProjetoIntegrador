package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.Genre

class GenreListingRepository {
    private val allGenres = listOf(
        Genre(1, "Ação"),
        Genre(2, "Anime"),
        Genre(3, "Biografia"),
        Genre(4, "Comédia"),
        Genre(5, "Drama"),
        Genre(6, "Animação"),
        Genre(7, "Família"),
    )

    fun getGenres() : List<Genre>{
        return allGenres
    }
}