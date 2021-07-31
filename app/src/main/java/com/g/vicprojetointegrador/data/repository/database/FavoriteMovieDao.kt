package com.g.vicprojetointegrador.data.repository.database

import androidx.room.*
import com.g.vicprojetointegrador.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface FavoriteMovieDao {
    // insert only favorite movies
    //Completable, single or maybe?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Completable //no return

    @Delete()
    fun deleteMovie(movie: Movie): Completable

    @Query("SELECT * from movies")
    fun getFavoriteMovies() : Observable<List<Movie>>

}