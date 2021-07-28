package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.MovieListingRepository

class GetPopularMoviesUseCase(private val repository: MovieListingRepository = MovieListingRepository()) {
        fun execute() = repository.getMovies()
}