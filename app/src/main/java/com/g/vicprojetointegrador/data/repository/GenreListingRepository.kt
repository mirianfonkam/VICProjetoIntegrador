package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.GenresResponse
import io.reactivex.rxjava3.core.Single

class GenreListingRepository {
    fun getGenres() : Single<GenresResponse> {
        return NetworkInstance.getService().getGenres()
    }
}

// move to test
//private val allGenres = listOf(
//    Genre(1, "Ação"),
//    Genre(2, "Anime"),
//    Genre(3, "Biografia"),
//    Genre(4, "Comédia"),
//    Genre(5, "Drama"),
//    Genre(6, "Animação"),
//    Genre(7, "Família"),
//)
//
//fun getGenres() : List<Genre>{
//    return allGenres
//}