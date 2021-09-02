package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRemoteRepository

class GetPopularMoviesUseCase(private val remoteRepository: MovieListingRemoteRepository = MovieListingRemoteRepository()) {
        operator fun invoke() = remoteRepository.getPopularMovies()
}