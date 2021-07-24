package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.repository.GenreListingRepository
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMovieList)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) {
            recyclerView.adapter = MovieAdapter(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner, { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        })

        viewModel.progressBar.observe(viewLifecycleOwner){  isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

        //Add chip to chipGroup dynamically
        val genres : List<Genre> = GenreListingRepository().getGenres()
        val chipGroup = view.findViewById<ChipGroup>(R.id.cgGenreList)


        for (genre in genres) {
            val chip = layoutInflater.inflate(R.layout.list_item_genre, chipGroup, false) as Chip
            chip.text = genre.name
            chipGroup.addView(chip as View)
        }
    }



//    private fun getMovies() {
//        val movieListingRepository = NetworkInstance.getService().
//
//        val movieViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                return MainViewModel(movieListingRepository) as T
//            }
//        }).get(MainViewModel::class.java)
//
//        movieViewModel.getPopularMovies()
//        movieViewModel.popularMovies
//            .observe(this, { popularMovies ->
//                movieAdapter.addMovies(popularMovies)
//            })
//        movieViewModel.error.observe(this, { error ->
//            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
//        })
//    }
    //getCheckedChipId()
    //setOnCheckedChangeListener()
    //onCheckedChanged


}