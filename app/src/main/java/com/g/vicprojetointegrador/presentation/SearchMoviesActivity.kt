package com.g.vicprojetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.databinding.ActivitySearchMoviesBinding
import com.g.vicprojetointegrador.presentation.adapter.MovieListAdapter
import com.g.vicprojetointegrador.presentation.viewmodel.SearchViewModel
import com.g.vicprojetointegrador.utils.TMDBConstants
import com.google.android.material.chip.Chip


class SearchMoviesActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySearchMoviesBinding
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        binding.tvBackHome.setOnClickListener {
           this.finish()
        }

        binding.svSearchQuery.requestFocus() // search view will have the cursor on when activity starts

        // Add chips to chipGroup dynamically
        searchViewModel.genresLiveData.observe(this){ genres ->
            for (genre in genres) {
                addChipToGenreChipGroup(genre)
            }
        }

        binding.svSearchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val textSubmitted: String = binding.svSearchQuery.query.toString()
                binding.svSearchQuery.clearFocus() // make keyboard disappear
                searchViewModel.searchMoviesByQuery(textSubmitted)
                return true // the query has been handled by the listener
            }

            override fun onQueryTextChange(newText: String): Boolean {
                binding.cgGenreList.clearCheck()
                return false // default search widget action
            }
        })

        binding.cgGenreList.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            chip?.let {chipView ->
                if (chipView.isChecked) {
                    binding.grpSearchNotFound.isVisible = false
                    binding.svSearchQuery.setQuery(chip.text,false)
                    binding.svSearchQuery.clearFocus()
                    chipView.isChecked = true
                    searchViewModel.getMoviesByGenre(checkedId.toString())
                }
            }
        }

        val movieListAdapter = MovieListAdapter(object : MovieClickListener{
            override fun onFavoriteClick(movie: Movie) {
               searchViewModel.favoriteClicked(movie)
            }

            override fun onMovieClick(movie: Movie) {
                openMovieDetails(movie)
            }
        })

        binding.rvMovieList.adapter = movieListAdapter

        populateMovieListingRV(movieListAdapter)

        handleSearchNotFound()

        handleProgressBar()
    }

    private fun populateMovieListingRV(movieListAdapter: MovieListAdapter) {
        searchViewModel.movieListingLiveData.observe(this) {
            movieListAdapter.submitList(it)
        }
    }

    private fun handleSearchNotFound() {
        searchViewModel.isSearchResultsEmpty.observe(this) { emptyResult ->
            binding.grpSearchNotFound.isVisible = emptyResult
        }
    }

    private fun handleProgressBar() {
        searchViewModel.progressBar.observe(this) { isLoading ->
             binding.progressBar.isVisible = isLoading
        }
    }

    private fun addChipToGenreChipGroup(genre: Genre) {
        val chip =
            layoutInflater.inflate(R.layout.item_genre_home, binding.cgGenreList, false) as Chip
        chip.id = genre.id
        chip.text = genre.name
        binding.cgGenreList.addView(chip as View)
    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra(TMDBConstants.EXTRA_MOVIE, movie)
        }
        startActivity(intent)
    }
}