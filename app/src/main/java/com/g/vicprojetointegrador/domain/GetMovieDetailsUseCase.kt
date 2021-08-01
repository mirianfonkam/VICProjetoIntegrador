package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRepository

class GetMovieDetailsUseCase(private val repository: MovieListingRepository = MovieListingRepository()) {
    fun execute(movieId : Int) = repository.getMovie(movieId)
}