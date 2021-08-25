package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRemoteRepository

class SearchMoviesByQueryUseCase(private val remoteRepository: MovieListingRemoteRepository = MovieListingRemoteRepository()) {
    fun execute(query : String) = remoteRepository.searchMovies(query)
}