package com.g.vicprojetointegrador.data.repository

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
    //Get a list of the current popular movies on TMDB. This list updates daily.
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
        @Query("with_genres") genreId: Int?,
        @Query("page") pageNumber: Int,
    ): Single<MoviesResponse>

    //Search for movies
    @GET("search/movie")
    fun getSearchResults(
        @Query("query") query: String
    ): Single<MoviesResponse>

    @GET("movie/{movie_id}?append_to_response=credits,release_dates")
    fun getMovieDetails(
         @Path("movie_id") movieId: Int
    ): Single<MovieDetails>

//    //Get the primary information about a movie.
//    @GET("movie/{movie_id}")
//    fun getMovieDetails(
//        @Path("movie_id") movieId: Int
//    ): Single<Movie>
//    //append_to_response This makes it possible to make sub requests within the same namespace
//    //these requests only count as one request against the rate limits to speed up the experience
//
//    //Get the cast and crew for a movie.
//    @GET("movie/{movie_id}/credits")
//    fun getMovieCredits(
//        @Path("movie_id") movieId: Int
//    ): Single<Credits>
//
//    //Get the release date along with the certification (maturityRating) for a movie
//    @GET("/movie/{movie_id}/release_dates")
//    fun getMovieCertification(
//        @Path("movie_id") movieId: Int,
//    ): Single<ReleaseInfo>


    //https://api.themoviedb.org/3/movie/157336?api_key=2770152fe1a0bbfa51de4dc91d640b1c&append_to_response=credits,release_dates
    // "iso_3166_1":"US"
}
