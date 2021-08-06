package com.g.vicprojetointegrador.data.repository

import android.content.Context
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.repository.database.MovieRoomDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

// Repository -> controls local database (MovieDatabase)
class MovieListingLocalRepository() {


    fun getFavoriteMovies(context: Context) : Observable<List<Movie>> {
        return MovieRoomDatabase.getDatabase(context).movieDao().getFavoriteMovies()
    }

    fun saveFavoriteMovies(movie: Movie, context: Context) : Completable {
        return MovieRoomDatabase.getDatabase(context).movieDao().insertMovie(movie)
    }

    fun deleteFavoriteMovies(movie: Movie, context: Context) : Completable {
        return MovieRoomDatabase.getDatabase(context).movieDao().deleteMovie(movie)
    }
}