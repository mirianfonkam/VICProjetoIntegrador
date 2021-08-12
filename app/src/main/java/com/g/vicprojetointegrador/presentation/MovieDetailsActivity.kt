package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Crew
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.presentation.adapter.PersonRVAdapter
import com.g.vicprojetointegrador.presentation.viewmodel.MovieDetailsViewModel
import com.g.vicprojetointegrador.utils.TMDBConstants
import com.g.vicprojetointegrador.utils.formatHourMinutes
import com.g.vicprojetointegrador.utils.formatPercentage
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.like.LikeButton
import com.like.OnLikeListener

/*
 * MovieDetailsActivity opened on MovieClick
 * Movie object is parsed with intent
 */
class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        // If user clicks on the return button, return to previous activity
        val fabBack = findViewById<FloatingActionButton>(R.id.fabBack)
        fabBack.setOnClickListener { this.finish() }

        // Binds views
        val ivBackdrop = findViewById<ImageView>(R.id.ivMovieBackdrop)
        val tvMovieTitle = findViewById<TextView>(R.id.tvMovieTitle)
        val tvMovieYear = findViewById<TextView>(R.id.tvMovieYear)
        val tvMovieOverview = findViewById<TextView>(R.id.tvMovieOverview)
        val tvMovieVoteAverage = findViewById<TextView>(R.id.tvMovieVoteAverage)
        val tvMovieRuntime = findViewById<TextView>(R.id.tvMovieRuntime)
        val tvMovieMaturityRating = findViewById<TextView>(R.id.tvMovieMaturityRating)
        val cgGenreList = findViewById<ChipGroup>(R.id.cgGenreList)
        val btnLike = findViewById<LikeButton>(R.id.btnLike)
        val rvPeopleList  = findViewById<RecyclerView>(R.id.rvMovieActors)

        // Gets parsed movie object properties
        val movie = intent.getParcelableExtra<Movie>(TMDBConstants.EXTRA_MOVIE)
        val movieId : Int = movie?.id!!
        val detailsViewModel : MovieDetailsViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieDetailsViewModel(movieId, this@MovieDetailsActivity.application) as T
            }
        }).get(MovieDetailsViewModel::class.java)

        // Pre-loaded data from the GetPopularMovies() request made in previous activity
        movie.backdropPath?.let {
            ivBackdrop.load("${TMDBConstants.IMAGE_HD_URL}${it}")
        }

        movie.run {
            tvMovieTitle.text = title
            tvMovieYear.text = releaseDate.take(4)
            tvMovieOverview.text = overview
            tvMovieVoteAverage.text = voteAverage.formatPercentage()
        }

        // New network request for additional movie data
        detailsViewModel.extraMovieDetailsLiveData.observe(this) { movieDetails ->
            movieDetails.run {
                tvMovieRuntime.text = runtime.formatHourMinutes()
            }

            rvPeopleList.adapter = PersonRVAdapter(movieDetails.credits.cast)

            val director : Crew? = movieDetails.credits.crew.firstOrNull { crew -> crew.job == "Director" }
            val name : String = director?.name ?: "--"

            val data =  movieDetails.releaseInfoResponse.results.mapNotNull {
                    releaseInfo -> releaseInfo.takeIf { it.countryCode == "US"}?.releaseDates?.mapNotNull{ it.maturityRating }}.firstOrNull()
            tvMovieMaturityRating.text = data?.filter { it.isNotEmpty() }?.firstNotNullOfOrNull { it }?.toString()


            // Adds chips of movie genre to chipGroup dynamically
            movieDetails.genres.let { genres ->
                for (genre in genres) {
                    val chip = layoutInflater.inflate(R.layout.item_genre_movie_tags, cgGenreList, false) as Chip
                    chip.text = genre.name
                    cgGenreList.addView(chip as View)
                }
            }
        }

        detailsViewModel.errorLiveData.observe(this, { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })

        // Sets the initial state of the like btn when page is opened
        detailsViewModel.isFavorited.observe(this) { btnFavoriteState ->
            btnLike.isLiked = btnFavoriteState
            movie.isFavorited = btnFavoriteState
        }

        // Listener to insert or delete movie from DB
        btnLike.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
                detailsViewModel.favoriteClicked(movie)
            }
            override fun unLiked(likeButton: LikeButton?) {
                detailsViewModel.favoriteClicked(movie)
            }
        })
    }
}