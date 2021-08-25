package com.g.vicprojetointegrador.domain

import com.g.vicprojetointegrador.data.repository.GenreListingRepository

class GetGenresUseCase(private val repository: GenreListingRepository = GenreListingRepository()) {
    fun execute() = repository.getGenres()
}