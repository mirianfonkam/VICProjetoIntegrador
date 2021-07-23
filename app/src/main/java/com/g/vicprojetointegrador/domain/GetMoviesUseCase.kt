package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRepository

class GetMoviesUseCase( private val repository: MovieListingRepository = MovieListingRepository()) {
        fun execute() = repository.getMovies()
}