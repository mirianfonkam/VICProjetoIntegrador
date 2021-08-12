package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.MovieDetails
import com.g.vicprojetointegrador.data.model.MoviesResponse
import com.g.vicprojetointegrador.data.repository.database.MovieRoomDatabase
import com.g.vicprojetointegrador.data.repository.network.NetworkInstance
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

//Repository -> controls remote source                                                   [NetworkInstance]
class MovieListingRemoteRepository() {
    private val movieDao by lazy { MovieRoomDatabase.getDatabase().movieDao() }

    fun searchMovies(query: String): Observable<MoviesResponse> {
        return NetworkInstance.getService().getSearchResults(query)
            .flatMap { response ->
                movieDao.getFavoriteMovies().map { movies ->
                    response.copy(results = response.results.map { movie ->
                        movie.copy(isFavorited = movies.any {
                            it.id == movie.id
                        })
                    })
                }
            }
    }

    fun getMovies(): Observable<MoviesResponse> {
        return NetworkInstance.getService().getPopularMovies()
            .flatMap { response ->
                movieDao.getFavoriteMovies().map { movies ->
                    response.copy(results = response.results.map { movie ->
                        movie.copy(isFavorited = movies.any {
                            it.id == movie.id
                        })
                    })
                }
            }
    }

    fun getMoviesByGenre(genreId: String): Observable<MoviesResponse> {
        return NetworkInstance.getService().getMoviesByGenre(genreId)
            .flatMap { response ->
                movieDao.getFavoriteMovies().map { movies ->
                    response.copy(results = response.results.map { movie ->
                        movie.copy(isFavorited = movies.any {
                            it.id == movie.id
                        })
                    })
                }
            }
    }

    fun getMovie(movieId: Int): Single<MovieDetails> {
        return NetworkInstance.getService().getMovieDetails(movieId)
    }


//    fun compareWithDatabase(instance : Observable<MoviesResponse>): Observable<MoviesResponse> = apply { instance
//        .flatMap { response ->
//            movieDao.getFavoriteMovies().map { movies ->
//                response.copy(results = response.results.map { movie ->
//                movie.copy(isFavorited = movies.any {
//                    it.id == movie.id
//                })
//            })}
//        }}

}





