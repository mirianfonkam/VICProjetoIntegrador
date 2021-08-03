package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRepository

class GetMoviesByGenreUseCase(private val repository: MovieListingRepository = MovieListingRepository()) {
    fun execute(genreId: String) = repository.getMoviesByGenre(genreId)
}