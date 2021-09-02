package com.g.vicprojetointegrador

import com.g.vicprojetointegrador.data.repository.MovieListingRemoteRepository
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.model.GenresResponse
import com.g.vicprojetointegrador.data.repository.network.MovieApiService
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @InjectMocks
    lateinit var movieRepository: MovieListingRemoteRepository

    @Mock
    lateinit var movieService: MovieApiService

    @Test
    fun getGenres() {
        val genres = listOf(
            Genre(id = 28, name="Ação"),
            Genre(id = 12, name = "Aventura"),
            Genre(id = 16, name = "Animação"),
            Genre(id = 35, name = "Comédia"),
            Genre(id = 80, name = "Crime"),
            Genre(id = 99, name = "Documentário"),
            Genre(id = 18, name = "Drama"),
            Genre(id = 10751, name = "Família"),
            Genre(id = 14, name = "Fantasia"),
            Genre(id = 36, name = "História"),
            Genre(id = 27, name = "Terror"),
            Genre(id = 10402, name = "Música"),
            Genre(id = 9648, name = "Mistério"),
            Genre(id = 10749, name = "Romance"),
            Genre(id = 878, name = "Ficção científica"),
            Genre(id = 10770, name = "Cinema TV"),
            Genre(id = 53, name = "Thriller"),
            Genre(id = 10752, name = "Guerra"),
            Genre(id = 37, name = "Faroeste"),
        )
        val response = GenresResponse(genres = genres)

        Mockito.`when`(movieService.getGenres())
            .thenReturn(Single.just(response))

        movieRepository.getGenres()
            .test()
            .await()
            .assertNoErrors()
            .assertValue(response)
    }

}