package com.g.vicprojetointegrador.domain

import android.content.Context
import com.g.vicprojetointegrador.data.repository.MovieListingRepository

class GetFavoriteMoviesUseCase(private val repository: MovieListingRepository = MovieListingRepository(), private val context: Context) {
    fun execute() = repository.getFavoriteMovies(context)
}