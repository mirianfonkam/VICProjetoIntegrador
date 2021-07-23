package com.g.vicprojetointegrador.data.repository

import com.g.vicprojetointegrador.data.model.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/*
 *
 * @Query -> Query Strings
 * @Path  -> Path Parameters
 */

interface MovieApiService {
    //Get a list of the current popular movies on TMDB. This list updates daily.
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = TMDBConstants.API_KEY
    ): Observable<MoviesResponse>

    //Get the list of official genres for movies.
    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String = TMDBConstants.API_KEY
    ): Observable<GenresResponse>

    //Discover movies by genres.
    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("with_genres") genreId: Int?,
        @Query("page") pageNumber: Int,
    ): Observable<MoviesResponse>

    //Search for movies
    @GET("search/movie")
    fun getSearchResults(
        @Query("query") query: String
    ): Observable<MoviesResponse>

    //Get the primary information about a movie.
    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): Observable<Movie>
    //append_to_response This makes it possible to make sub requests within the same namespace i

    //Get the cast and crew for a movie.
    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int
    ): Observable<CreditsResponse>

    //Get the release date along with the certification (maturityRating) for a movie
    @GET("/movie/{movie_id}/release_dates")
    fun getMovieCertification(
        @Path("movie_id") movieId: Int,
    ): Observable<ReleaseDatesResponse>

}
