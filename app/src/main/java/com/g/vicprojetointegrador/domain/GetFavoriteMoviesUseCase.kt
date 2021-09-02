package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingLocalRepository

class GetFavoriteMoviesUseCase(private val localRepository: MovieListingLocalRepository = MovieListingLocalRepository(),) {
    operator fun invoke() = localRepository.getFavoriteMovies()
}