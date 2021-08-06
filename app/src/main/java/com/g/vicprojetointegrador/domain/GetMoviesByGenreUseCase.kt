package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRemoteRepository

class GetMoviesByGenreUseCase(private val remoteRepository: MovieListingRemoteRepository = MovieListingRemoteRepository()) {
    fun execute(genreId: String) = remoteRepository.getMoviesByGenre(genreId)
}