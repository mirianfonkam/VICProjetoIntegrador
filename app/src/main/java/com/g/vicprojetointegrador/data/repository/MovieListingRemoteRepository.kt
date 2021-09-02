package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.GenresResponse
import com.g.vicprojetointegrador.data.model.MovieDetails
import com.g.vicprojetointegrador.data.model.MoviesResponse
import com.g.vicprojetointegrador.data.repository.database.MovieRoomDatabase
import com.g.vicprojetointegrador.data.repository.network.MovieApiService
import com.g.vicprojetointegrador.data.repository.network.NetworkInstance
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

//Repository -> controls remote source
class MovieListingRemoteRepository(private val network: MovieApiService = NetworkInstance.getService()  ) {
    private val movieDao by lazy { MovieRoomDatabase.getDatabase().movieDao() }

    fun searchMovies(query: String): Observable<MoviesResponse> {
        return network.getSearchResults(query).compareWithLocalMovies()
    }

    fun getPopularMovies(): Observable<MoviesResponse> {
        return network.getPopularMovies().compareWithLocalMovies()
    }

    fun getMoviesByGenre(genreId: String): Observable<MoviesResponse> {
        return network.getMoviesByGenre(genreId).compareWithLocalMovies()
    }

    fun getMovie(movieId: Int): Single<MovieDetails> {
        return network.getMovieDetails(movieId)
    }

    fun getGenres() : Single<GenresResponse> {
        return network.getGenres()
    }

    private fun Observable<MoviesResponse>.compareWithLocalMovies() : Observable<MoviesResponse> {
        return this.flatMap { response ->
            movieDao.getFavoriteMovies().map { movies ->
                response.copy(results = response.results.map { movie ->
                    movie.copy(isFavorited = movies.any {
                        it.id == movie.id
                    })
                })}
        }
    }

}








