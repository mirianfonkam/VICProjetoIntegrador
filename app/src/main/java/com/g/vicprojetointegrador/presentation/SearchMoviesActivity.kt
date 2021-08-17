package com.g.vicprojetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.presentation.adapter.MovieListAdapter
import com.g.vicprojetointegrador.presentation.viewmodel.SearchViewModel
import com.g.vicprojetointegrador.utils.TMDBConstants
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class SearchMoviesActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)

        val svSearchQuery = findViewById<SearchView>(R.id.svSearchQuery)
        val cgGenreList = findViewById<ChipGroup>(R.id.cgGenreList)
        val grpSearchNotFound = findViewById<Group>(R.id.grpSearchNotFound)
        val rvMovieList = findViewById<RecyclerView>(R.id.rvMovieList)
        val searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val tvBackHome = findViewById<TextView>(R.id.tvBackHome)

        tvBackHome.setOnClickListener {
           this.finish()
        }

        svSearchQuery.requestFocus() // search view will have the cursor on when activity starts

        // Add chips to chipGroup dynamically
        searchViewModel.genresLiveData.observe(this){ genres ->
            for (genre in genres) {
                val chip = layoutInflater.inflate(R.layout.item_genre_home, cgGenreList, false) as Chip
                chip.id = genre.id
                chip.text = genre.name
                cgGenreList.addView(chip as View)
            }
        }

        svSearchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                val textSubmitted: String =  svSearchQuery.query.toString()
                svSearchQuery.clearFocus() // make keyboard disappear
                searchViewModel.searchMoviesByQuery(textSubmitted)
                return true // the query has been handled by the listener
            }

            override fun onQueryTextChange(newText: String): Boolean {
                cgGenreList.clearCheck()
                return false // default search widget action
            }
        })

        searchViewModel.isSearchResultsEmpty.observe(this){ emptyResult ->
                grpSearchNotFound.isVisible = emptyResult
        }

        cgGenreList.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            chip?.let {chipView ->
                if (chipView.isChecked) {
                    searchViewModel.getMoviesByGenre(checkedId.toString())
                    grpSearchNotFound.isVisible = false
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

        rvMovieList.adapter = movieListAdapter

        searchViewModel.movieListingLiveData.observe(this) {
            movieListAdapter.submitList(it)
            Log.i("debug", "observing list in rvAdapter $it")
        }

    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra(TMDBConstants.EXTRA_MOVIE, movie)
        }
        startActivity(intent)
    }
}