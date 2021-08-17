package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Crew
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.databinding.ActivityMovieDetailsBinding
import com.g.vicprojetointegrador.presentation.adapter.PersonRVAdapter
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

        binding.fabBack.setOnClickListener { this.finish() }

        // Gets parsed movie object properties
        val movie = intent.getParcelableExtra<Movie>(TMDBConstants.EXTRA_MOVIE)
        val movieId: Int = movie?.id!!
        val detailsViewModel: MovieDetailsViewModel =
            ViewModelProvider(this, object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return MovieDetailsViewModel(
                        movieId,
                        this@MovieDetailsActivity.application
                    ) as T
                }
            }).get(MovieDetailsViewModel::class.java)

        // Pre-loaded data from the GetPopularMovies() request made in previous activity
        movie.backdropPath?.let {
            binding.ivMovieBackdrop.load("${TMDBConstants.IMAGE_HD_URL}${it}")
        }

        movie.run {
            binding.tvMovieTitle.text = title
            binding.tvMovieYear.text = releaseDate?.take(4) ?: "--"
            binding.tvMovieOverview.text = overview
            binding.tvMovieVoteAverage.text = voteAverage.formatPercentage()
        }

        // New network request for additional movie data
        detailsViewModel.extraMovieDetailsLiveData.observe(this) { movieDetails ->
            movieDetails.run {
                binding.tvMovieRuntime.text = runtime.formatHourMinutes()
            }

            binding.rvMovieActors.adapter = PersonRVAdapter(movieDetails.credits.cast)

            val director: Crew? =
                movieDetails.credits.crew.firstOrNull { crew -> crew.job == "Director" }

            binding.tvPersonName.text = director?.name ?: "--"
            binding.tvPersonRole.text = director?.job

            binding.ivPersonProfile.load("${TMDBConstants.IMAGE_URL}${director?.profilePath}") {
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            val data =
                movieDetails.releaseInfoResponse.results.mapNotNull { releaseInfo -> releaseInfo.takeIf { it.countryCode == "US" }?.releaseDates?.mapNotNull { it.maturityRating } }
                    .firstOrNull()
            binding.tvMovieMaturityRating.text =
                data?.filter { it.isNotEmpty() }?.firstNotNullOfOrNull { it }?.toString()


            // Adds chips of movie genre to chipGroup dynamically
            movieDetails.genres.let { genres ->
                for (genre in genres) {
                    addChipsToGenreChipGroup(genre)
                }
            }
        }

        handleErrorOnLoadingMovieDetails(detailsViewModel)

        setFavoriteBtnInitialState(detailsViewModel, movie)

        handleFavoriteBtnClick(detailsViewModel, movie)

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