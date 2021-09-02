package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRemoteRepository

class GetMovieDetailsUseCase(private val remoteRepository: MovieListingRemoteRepository = MovieListingRemoteRepository()) {
    operator fun invoke(movieId : Int) = remoteRepository.getMovie(movieId)
}