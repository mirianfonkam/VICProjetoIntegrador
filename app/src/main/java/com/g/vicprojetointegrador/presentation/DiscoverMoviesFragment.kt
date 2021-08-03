package com.g.vicprojetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.presentation.adapter.MovieListAdapter
import com.g.vicprojetointegrador.presentation.viewmodel.MainViewModel
import com.g.vicprojetointegrador.utils.TMDBConstants

/*
 * List the popular movies
 * this is the default tab pager
 */
class DiscoverMoviesFragment : Fragment(), MovieListAdapter.MovieClickListener {
    lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = DiscoverMoviesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_discover_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMovieList)

        val movieListAdapter = MovieListAdapter(this)
        recyclerView.adapter = movieListAdapter

        //val chipGroup = view.findViewById<ChipGroup>(R.id.cgGenreList)

        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) {
           movieListAdapter.submitList(it)
        }

    }

    override fun onMovieClick(movie: Movie) {
        openMovieDetails(movie)
    }

    override fun favoriteClicked(movie: Movie) {
        //viewModel.favoriteClicked(movie)
    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java).apply {
            putExtra(TMDBConstants.EXTRA_MOVIE, movie)
        }
        startActivity(intent)
    }






}