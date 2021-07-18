package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Genre
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.repository.GenreListingRepository
import com.g.vicprojetointegrador.data.repository.MoviesRepository
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
        val data : List<Movie> = MoviesRepository().getMovies()
        recyclerView.adapter = MovieAdapter(data)

        //Add chip to chipGroup dynamically
        val genres : List<Genre> = GenreListingRepository().getGenres()
        val chipGroup = view.findViewById<ChipGroup>(R.id.cgGenreList)


        for (genre in genres) {
            val chip = layoutInflater.inflate(R.layout.list_item_genre, chipGroup, false) as Chip
            chip.text = genre.name
            chipGroup.addView(chip as View)
        }
    }

    //getCheckedChipId()
    //setOnCheckedChangeListener()
    //onCheckedChanged


}