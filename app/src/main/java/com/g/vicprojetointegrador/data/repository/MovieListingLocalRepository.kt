package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.repository.database.MovieRoomDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

// Local Repository -> controls local database (MovieDatabase)
class MovieListingLocalRepository() {
    private val movieDao by lazy { MovieRoomDatabase.getDatabase().movieDao() }

    fun getFavoriteMovies() : Observable<List<Movie>> {
        return movieDao.getFavoriteMovies()
    }

    fun checkFavoriteStatus(movieId: Int) : Single<Boolean> {
        return movieDao.checkFavoriteStatus(movieId)
    }

    fun saveFavoriteMovies(movie: Movie) : Completable {
        return movieDao.insertMovie(movie)
    }

    fun deleteFavoriteMovies(movie: Movie) : Completable {
        return movieDao.deleteMovie(movie)
    }
}