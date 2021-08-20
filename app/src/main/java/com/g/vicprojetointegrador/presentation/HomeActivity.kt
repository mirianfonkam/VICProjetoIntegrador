package com.g.vicprojetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.databinding.ActivityHomeBinding
import com.g.vicprojetointegrador.presentation.adapter.PagerSectionAdapter
import com.g.vicprojetointegrador.presentation.viewmodel.MovieSharedViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/*
 * Activity on application launch
 * It holds two fragments/tabs: 1. DiscoverMovies 2. FavoriteMovies
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MovieSharedViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesViewModel = ViewModelProvider(this).get(MovieSharedViewModel::class.java)

        binding.pagerMovieList.adapter = PagerSectionAdapter(supportFragmentManager, lifecycle)

        configureTabPager(binding.tabPageSection,binding.pagerMovieList)

        handleGenericError()

        // Adds chips to chipGroup dynamically
        moviesViewModel.genresLiveData.observe(this) { genres ->
            for (genre in genres) {
                val chip =
                    layoutInflater.inflate(R.layout.item_genre_home, binding.cgGenreList, false) as Chip
                chip.id = genre.id
                chip.text = genre.name
                binding.cgGenreList.addView(chip as View)
            }
        }

        // set chip group checked change listener
        binding.cgGenreList.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            // Responds to child chip checked
            chip?.let { chipView ->
                if (chipView.isChecked) {
                    moviesViewModel.getMoviesByGenre(checkedId.toString())
                }
            }
            // All chips are unchecked returns to displaying popular movies
            if (checkedId == -1) {
                moviesViewModel.getPopularMovies()
            }
        }

        binding.svSearchQuery.setOnQueryTextFocusChangeListener { thisView, hasFocus ->
            if (hasFocus) {
                thisView.clearFocus() // onResume the focus will be cleared
                openSearchActivity()
            }
        }
    }

    private fun handleGenericError() {
        moviesViewModel.errorLiveData.observe(this, { error ->
            openGenericErrorActivity()
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }

    private fun openGenericErrorActivity() {
        val intent = Intent(this, GenericErrorActivity::class.java)
        startActivity(intent)
    }

    private fun openSearchActivity() {
        val intent = Intent(this, SearchMoviesActivity::class.java)
        startActivity(intent)
    }

    private fun configureTabPager(tabPagerSection: TabLayout, pagerMovieList: ViewPager2) {
        pagerMovieList.isUserInputEnabled = false
        TabLayoutMediator(tabPagerSection, pagerMovieList){ tab, position ->
            when (position) {
                0 -> {
                    tab.setText(R.string.all_movies)
                }
                1 -> {
                    tab.setText(R.string.favorite)
                }
            }
        }.attach()
    }

}


