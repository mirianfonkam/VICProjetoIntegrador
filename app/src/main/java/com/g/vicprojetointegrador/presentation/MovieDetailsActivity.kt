package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Crew
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.databinding.ActivityMovieDetailsBinding
import com.g.vicprojetointegrador.presentation.adapter.PersonRVAdapter
import com.g.vicprojetointegrador.presentation.viewmodel.MovieDetailsVMFactory
import com.g.vicprojetointegrador.presentation.viewmodel.MovieDetailsViewModel
import com.g.vicprojetointegrador.utils.TMDBConstants
import com.g.vicprojetointegrador.utils.formatHourMinutes
import com.g.vicprojetointegrador.utils.formatPercentage
import com.google.android.material.chip.Chip
import com.like.LikeButton
import com.like.OnLikeListener

/*
 * MovieDetailsActivity opened on MovieClick
 * Movie object is parsed with intent
 */
class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gets parsed movie object properties
        val movie = intent.getParcelableExtra<Movie>(TMDBConstants.EXTRA_MOVIE)
        val movieId: Int = movie?.id!!

        val detailsViewModel: MovieDetailsViewModel =
            ViewModelProvider(this, MovieDetailsVMFactory(movieId))
                .get(MovieDetailsViewModel::class.java)

        // Pre-loaded data from the GetPopularMovies() request made in previous activity
        movie.backdropPath?.let {
            binding.ivMovieBackdrop.load("${TMDBConstants.IMAGE_HD_URL}${it}")
        }

        movie.run {
            binding.tvMovieTitle.text = title
            binding.tvMovieYear.text = releaseDate?.take(4)
            binding.tvMovieOverview.text = overview
            binding.tvMovieVoteAverage.text = voteAverage.formatPercentage()
        }

        // New network request for additional movie data
        detailsViewModel.extraMovieDetailsLiveData.observe(this) { movieDetails ->
            movieDetails.run {
                binding.tvMovieRuntime.text = duration
                binding.tvMovieMaturityRating.text = certification
                binding.tvPersonName.text = director?.name
                binding.tvPersonRole.text = director?.job
            }

            binding.rvMovieActors.adapter = PersonRVAdapter(movieDetails.actors)

            binding.ivPersonProfile.load(movieDetails.director?.profileImageUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
                fallback(R.drawable.ic_profile_no_image)
            }

            // Adds chips of movie genre to chipGroup dynamically
            movieDetails.genres.let { genres ->
                for (genre in genres) {
                    addChipsToGenreChipGroup(genre)
                }
            }
        }

        handleBackButtonPressed()

        handleErrorOnLoadingMovieDetails(detailsViewModel)

        setFavoriteBtnInitialState(detailsViewModel, movie)

        handleFavoriteBtnClick(detailsViewModel, movie)

    }

    private fun handleBackButtonPressed() {
        binding.fabBack.setOnClickListener { this.finish() }
    }

    private fun addChipsToGenreChipGroup(genre: Genre) {
        val chip = layoutInflater.inflate(
            R.layout.item_genre_movie_tags, binding.cgGenreList, false
        ) as Chip
        chip.text = genre.name
        binding.cgGenreList.addView(chip as View)
    }

    private fun handleErrorOnLoadingMovieDetails(detailsViewModel: MovieDetailsViewModel) {
        detailsViewModel.errorLiveData.observe(this, { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }

    private fun handleFavoriteBtnClick(
        detailsViewModel: MovieDetailsViewModel,
        movie: Movie
    ) {
        binding.btnLike.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
                detailsViewModel.favoriteClicked(movie)
            }

            override fun unLiked(likeButton: LikeButton?) {
                detailsViewModel.favoriteClicked(movie)
            }
        })
    }

    private fun setFavoriteBtnInitialState(
        detailsViewModel: MovieDetailsViewModel,
        movie: Movie
    ) {
        detailsViewModel.isFavorited.observe(this) { btnFavoriteState ->
            binding.btnLike.isLiked = btnFavoriteState
            movie.isFavorited = btnFavoriteState
        }
    }
}