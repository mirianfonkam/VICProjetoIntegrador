package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRemoteRepository

class SearchMoviesByQueryUseCase(private val remoteRepository: MovieListingRemoteRepository = MovieListingRemoteRepository()) {
    operator fun invoke(query : String) = remoteRepository.searchMovies(query)
}