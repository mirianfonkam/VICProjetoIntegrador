package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.model.TMDBConstants
import com.g.vicprojetointegrador.utils.formatPercentage

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val ivBackdrop = findViewById<ImageView>(R.id.ivMovieBackdrop)
        val tvMovieTitle = findViewById<TextView>(R.id.tvMovieTitle)
        val tvMovieYear = findViewById<TextView>(R.id.tvMovieYear)
        val tvMovieOverview = findViewById<TextView>(R.id.tvMovieOverview)
        val tvMovieVoteAverage = findViewById<TextView>(R.id.tvMovieVoteAverage)
        val tvMovieRuntime = findViewById<TextView>(R.id.tvMovieRuntime)

        val movie = intent.getParcelableExtra<Movie>(TMDBConstants.EXTRA_MOVIE)
        movie?.run {
            ivBackdrop.load("${TMDBConstants.IMAGE_HD_URL}${movie.backdropPath}")
            tvMovieTitle.text = title
            tvMovieYear.text = releaseDate.take(4)
            tvMovieOverview.text = overview
            tvMovieVoteAverage.text = voteAverage.formatPercentage()
            tvMovieRuntime.text = runtime.toString()
//
        }


        //favoriteIcon if isFa

        //transformations(CircleCropTransformation())
    }
}