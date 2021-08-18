package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.repository.MovieListingLocalRepository

class DeleteFavoriteMovieUseCase(private val localRepository: MovieListingLocalRepository = MovieListingLocalRepository()) {
    fun execute(movie: Movie) = localRepository.deleteFavoriteMovies(movie)
}