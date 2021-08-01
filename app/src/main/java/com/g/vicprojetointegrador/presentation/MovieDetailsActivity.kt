package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.presentation.viewmodel.MovieDetailsViewModel
import com.g.vicprojetointegrador.utils.TMDBConstants
import com.g.vicprojetointegrador.utils.formatHourMinutes
import com.g.vicprojetointegrador.utils.formatPercentage
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        val fabBack = findViewById<FloatingActionButton>(R.id.fabBack)
        fabBack.setOnClickListener { this.finish() }

        val ivBackdrop = findViewById<ImageView>(R.id.ivMovieBackdrop)
        val tvMovieTitle = findViewById<TextView>(R.id.tvMovieTitle)
        val tvMovieYear = findViewById<TextView>(R.id.tvMovieYear)
        val tvMovieOverview = findViewById<TextView>(R.id.tvMovieOverview)
        val tvMovieVoteAverage = findViewById<TextView>(R.id.tvMovieVoteAverage)
        val tvMovieRuntime = findViewById<TextView>(R.id.tvMovieRuntime)

        val movie = intent.getParcelableExtra<Movie>(TMDBConstants.EXTRA_MOVIE)

        //Pre-loaded data from the GetPopularMovies() request made in previous activity
        movie?.run {
            ivBackdrop.load("${TMDBConstants.IMAGE_HD_URL}${movie.backdropPath}")
            tvMovieTitle.text = title
            tvMovieYear.text = releaseDate.take(4)
            tvMovieOverview.text = overview
            tvMovieVoteAverage.text = voteAverage.formatPercentage()
        }

        // val id : Int = movie?.id!!
        // val id : Int = movie!!.id

        val id : Int = movie?.id!!

        val detailsViewModel : MovieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsViewModel(id)::class.java)

        //New network request for additional movie data
        detailsViewModel.movieLiveData.observe(this) { movieDetails ->
            movieDetails?.run {
                tvMovieRuntime.text = runtime.formatHourMinutes()
            }
        }

        detailsViewModel.errorLiveData.observe(this, { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })


        //favoriteIcon if isFa

        //transformations(CircleCropTransformation())
    }
}