package com.g.vicprojetointegrador.data.repository.network

import com.g.vicprojetointegrador.data.model.GenresResponse
import com.g.vicprojetointegrador.data.model.MovieDetails
import com.g.vicprojetointegrador.data.model.MoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/*
 *
 * @Query -> Query Strings
 * @Path  -> Path Parameters
 */

interface MovieApiService {
    //Get a list of the current popular movies on TMDB.
    @GET("movie/popular")
    fun getPopularMovies(
    ): Single<MoviesResponse>

    //Get the list of official genres for movies.
    @GET("genre/movie/list")
    fun getGenres(
    ): Single<GenresResponse>

    //Discover movies by genres.
    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("with_genres") genreId: String
    ): Single<MoviesResponse>

    //Search for movies
    @GET("search/movie")
    fun getSearchResults(
        @Query("query") query: String
    ): Single<MoviesResponse>

    // Get extra movie details - append_to_response makes it possible to make sub requests within the same namespace
    @GET("movie/{movie_id}?append_to_response=credits,release_dates")
    fun getMovieDetails(
         @Path("movie_id") movieId: Int
    ): Single<MovieDetails>
    //these requests only count as one request against the rate limits to speed up the experience


    //https://api.themoviedb.org/3/movie/157336?api_key=2770152fe1a0bbfa51de4dc91d640b1c&append_to_response=credits,release_dates
    // "iso_3166_1":"US"
}
