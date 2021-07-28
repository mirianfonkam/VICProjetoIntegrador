package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.model.TMDBConstants

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = intent.getParcelableExtra<Movie>(TMDBConstants.EXTRA_MOVIE)

        //favoriteIcon if isFa
    }
}