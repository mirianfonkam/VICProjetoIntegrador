package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingLocalRepository

class CheckFavoriteStatusUseCase(private val localRepository: MovieListingLocalRepository = MovieListingLocalRepository()) {
    operator fun invoke(movieId: Int) = localRepository.checkFavoriteStatus(movieId)
}