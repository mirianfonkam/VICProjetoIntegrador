package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingLocalRepository

class GetFavoriteMoviesUseCase(private val localRepository: MovieListingLocalRepository = MovieListingLocalRepository(),) {
    fun execute() = localRepository.getFavoriteMovies()
}