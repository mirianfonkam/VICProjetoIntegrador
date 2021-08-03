package com.g.vicprojetointegrador.data.repository

import android.content.Context
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.model.MovieDetails
import com.g.vicprojetointegrador.data.model.MoviesResponse
import com.g.vicprojetointegrador.data.repository.database.MovieRoomDatabase
import com.g.vicprojetointegrador.data.repository.network.NetworkInstance
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

//Repository -> controls remote source (NetworkInstance) and local database (MovieDatabase)
class MovieListingRepository() {

    fun getMovies() : Single<MoviesResponse> {
        return NetworkInstance.getService().getPopularMovies()
    }

    fun getMoviesByGenre(genreId : String) : Single<MoviesResponse> {
        return NetworkInstance.getService().getMoviesByGenre(genreId)
    }

    fun getMovie(movieId: Int): Single<MovieDetails>{
        return NetworkInstance.getService().getMovieDetails(movieId)
    }

    fun getFavoriteMovies(context: Context) : Observable<List<Movie>> {
        return MovieRoomDatabase.getInstance(context).movieDao().getFavoriteMovies()
    }
}





















// MOVE IT TO TEST
//    private val allMovies = listOf(
//        Movie(1,"Ford v Ferrari",
//            "American car designer Carroll Shelby and drive. Miles battle corporate " +
//                    "interference and the laws of physics to build a revolutionary race car for Ford.",
//            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dR1Ju50iudrOh3YgfwkAU1g2HZe.jpg",
//            80.0,
//            "11/14/2019",
//            175,
//            listOf(1,3,5)),
//        Movie(2,"Frozen",
//            "Young princess Anna of Arendelle dreams about finding true love at her sister Elsa’s coronation." +
//                    " Fate takes her on a dangerous journey in an attempt to end the eternal winter that has fallen over the kingdom.",
//            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbPrrbt8bSLcHSBCHnRclPlMZPl.jpg",
//            73.0,
//            "11/27/2013",
//            142,
//            listOf(1,3,5)),
//        Movie(3,
//            "Into the Wild",
//            "After graduating from Emory University in 1992, top student and athlete Christopher McCandless abandons his possessions, " +
//                    "gives his entire \$24,000 savings account to charity, and hitchhikes to Alaska to live in the wilderness." ,
//            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2MSGZEE6XZd2r4ODNziwAw7Hpw0.jpg",
//            78.0,
//            "10/19/2007",
//            128,
//            listOf(5))
//    )


