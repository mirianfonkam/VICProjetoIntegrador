package com.g.vicprojetointegrador.domain

import android.content.Context
import com.g.vicprojetointegrador.data.repository.MovieListingLocalRepository

class GetFavoriteMoviesUseCase(private val context: Context, private val localRepository: MovieListingLocalRepository = MovieListingLocalRepository(),) {
    fun execute() = localRepository.getFavoriteMovies(context)
}