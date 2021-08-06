package com.g.vicprojetointegrador.domain

import android.content.Context
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.repository.MovieListingLocalRepository

class SaveFavoriteMovieUseCase(private val context: Context, private val localRepository: MovieListingLocalRepository = MovieListingLocalRepository()) {
    fun execute(movie: Movie) = localRepository.saveFavoriteMovies(movie, context)
}