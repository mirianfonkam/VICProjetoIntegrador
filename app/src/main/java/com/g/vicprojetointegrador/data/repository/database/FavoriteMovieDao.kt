package com.g.vicprojetointegrador.data.repository.database

import androidx.room.*
import com.g.vicprojetointegrador.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Completable

    @Delete()
    fun deleteMovie(movie: Movie): Completable

    @Query("SELECT * from movies")
    fun getFavoriteMovies() : Observable<List<Movie>>


    @Query("SELECT EXISTS(SELECT 1 from movies WHERE id = :movieId)")
    fun checkFavoriteStatus(movieId : Int) : Single<Boolean>

}