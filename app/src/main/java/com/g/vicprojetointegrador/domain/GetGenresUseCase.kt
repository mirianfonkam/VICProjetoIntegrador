package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRemoteRepository

class GetGenresUseCase(private val remoteRepository: MovieListingRemoteRepository = MovieListingRemoteRepository()) {
    fun execute() = remoteRepository.getGenres()
}