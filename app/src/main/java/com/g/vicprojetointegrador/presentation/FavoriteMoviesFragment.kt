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
import com.g.vicprojetointegrador.presentation.clicklistener.MovieClickListener
import com.g.vicprojetointegrador.presentation.viewmodel.MovieSharedViewModel
import com.g.vicprojetointegrador.utils.TMDBConstants


/*
 * Favorite movies tab pager
 * movies that the user has liked
 * which are stored in room DB
 */
class FavoriteMoviesFragment : Fragment(), MovieClickListener {
    lateinit var moviesViewModel: MovieSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel = ViewModelProvider(requireActivity()).get(MovieSharedViewModel::class.java)

        val rvMovieList = view.findViewById<RecyclerView>(R.id.rvMovieList)

        val movieListAdapter = MovieListAdapter(this)
        rvMovieList.adapter = movieListAdapter

        moviesViewModel.favoriteMoviesLiveData.observe(viewLifecycleOwner) {
            movieListAdapter.submitList(it)
        }
    }

    override fun onMovieClick(movie: Movie) {
        openMovieDetails(movie)
    }

    override fun onFavoriteClick(movie: Movie) {
        moviesViewModel.favoriteClicked(movie)
    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java).apply {
            putExtra(TMDBConstants.EXTRA_MOVIE, movie)
        }
        startActivity(intent)
    }
}