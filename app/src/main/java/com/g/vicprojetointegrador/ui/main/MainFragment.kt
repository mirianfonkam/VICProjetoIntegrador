package com.g.vicprojetointegrador.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.g.vicprojetointegrador.MovieAdapter
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.repository.MoviesRepository

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2MSGZEE6XZd2r4ODNziwAw7Hpw0.jpg

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMovieList)
        val data : List<Movie> = MoviesRepository().getMovies()
        recyclerView.adapter = MovieAdapter(data)
    }

}