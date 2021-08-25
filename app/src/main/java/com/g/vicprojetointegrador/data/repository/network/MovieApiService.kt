package com.g.vicprojetointegrador.data.repository.network

import com.g.vicprojetointegrador.data.model.GenresResponse
import com.g.vicprojetointegrador.data.model.MovieDetails
import com.g.vicprojetointegrador.data.model.MoviesResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/*
 * @Query -> Query Strings
 * @Path  -> Path Parameters
 */

interface MovieApiService {
    //Get a list of the current popular movies on TMDB.
    @GET("movie/popular")
    fun getPopularMovies(
    ): Observable<MoviesResponse>

    //Discover movies by genres.
    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("with_genres") genreId: String
    ): Observable<MoviesResponse>

    //Search for movies. Pass a text query to search (required). This value should be URI encoded.
    @GET("search/movie")
    fun getSearchResults(
        @Query("query") query: String
    ): Observable<MoviesResponse>

    //Get the list of official genres for movies.
    @GET("genre/movie/list")
    fun getGenres(
    ): Single<GenresResponse>

    // Get extra movie details - append_to_response makes it possible to make sub requests within the same namespace
    @GET("movie/{movie_id}?append_to_response=credits,release_dates")
    fun getMovieDetails(
         @Path("movie_id") movieId: Int
    ): Single<MovieDetails>
    //these requests only count as one request against the rate limits to speed up the experience

}
