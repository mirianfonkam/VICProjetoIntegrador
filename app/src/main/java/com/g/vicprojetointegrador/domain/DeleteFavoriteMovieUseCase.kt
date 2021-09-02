package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.repository.MovieListingLocalRepository

class DeleteFavoriteMovieUseCase(private val localRepository: MovieListingLocalRepository = MovieListingLocalRepository()) {
    operator fun invoke(movie: Movie) = localRepository.deleteFavoriteMovies(movie)
}