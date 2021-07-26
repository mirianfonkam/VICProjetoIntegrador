package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.g.vicprojetointegrador.R


class DiscoverMoviesFragment : Fragment() {
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
        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) {
            recyclerView.adapter = MovieListAdapter(it)
        }

    }

    //getCheckedChipId()
    //setOnCheckedChangeListener()
    //onCheckedChanged


}