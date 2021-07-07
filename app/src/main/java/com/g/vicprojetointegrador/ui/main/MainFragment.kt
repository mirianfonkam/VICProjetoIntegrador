package com.g.vicprojetointegrador.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.g.vicprojetointegrador.Movie
import com.g.vicprojetointegrador.MovieAdapter
import com.g.vicprojetointegrador.R

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

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMovieList)
        val data : MutableList<Movie> = ArrayList()
        for (i in 1..10){
            data.add(Movie("Title $i"))
        }
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)

        recyclerView.adapter = MovieAdapter(data)
        recyclerView.layoutManager = layoutManager
    }

}