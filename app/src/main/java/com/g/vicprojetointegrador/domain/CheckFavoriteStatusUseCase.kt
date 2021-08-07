package com.g.vicprojetointegrador.domain

import android.content.Context
import com.g.vicprojetointegrador.data.repository.MovieListingLocalRepository

class CheckFavoriteStatusUseCase(private val context: Context, private val localRepository: MovieListingLocalRepository = MovieListingLocalRepository()) {
    fun execute(movieId: Int) = localRepository.checkFavoriteStatus(movieId, context)
}