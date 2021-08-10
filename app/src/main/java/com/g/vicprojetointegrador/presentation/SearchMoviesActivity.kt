package com.g.vicprojetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
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
        val chipGroup = findViewById<ChipGroup>(R.id.cgGenreList)
        val grpSearchNotFound = findViewById<Group>(R.id.grpSearchNotFound)
        val recyclerView = findViewById<RecyclerView>(R.id.rvMovieList)
        val searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val tvBackHome = findViewById<TextView>(R.id.tvBackHome)

        tvBackHome.setOnClickListener {
           this.finish()
        }

        svSearchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                svSearchQuery.setIconified(true)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })



//        svSearchQuery.setOnCloseListener(SearchView.OnCloseListener {
//            returnToHomeActivity()
//            true
//        })

        //        object OnCloseListener {
//            fun onClose(): Boolean
//        }



        // Refactor code in Home Activity
        // Add chips to chipGroup dynamically
        searchViewModel.genresLiveData.observe(this){ genres ->
            for (genre in genres) {
                val chip = layoutInflater.inflate(R.layout.item_genre_home, chipGroup, false) as Chip
                chip.id = genre.id
                chip.text = genre.name
                chipGroup.addView(chip as View)
            }
        }

        // set chip group checked change listener
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            // Responds to child chip checked
            chip?.let {chipView ->
                if (chipView.isChecked) {
                    searchViewModel.getMoviesByGenre(checkedId.toString())
                }
            }
//            // All chips are unchecked
//            if (checkedId == -1) {
//                //moviesViewModel.getSearchResultMovies()
//            }
        }


        val movieListAdapter = MovieListAdapter(object : MovieClickListener{
            override fun favoriteClicked(movie: Movie) {
                TODO("Not yet implemented")
            }

            override fun onMovieClick(movie: Movie) {
                TODO("Not yet implemented")
            }
        })

        recyclerView.adapter = movieListAdapter

        searchViewModel.movieListingLiveData.observe(this) {
            movieListAdapter.submitList(it)
            Log.i("debug", "observing list in rvAdapter $it")
        }

        //getQuery()

    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra(TMDBConstants.EXTRA_MOVIE, movie)
        }
        startActivity(intent)
    }
}