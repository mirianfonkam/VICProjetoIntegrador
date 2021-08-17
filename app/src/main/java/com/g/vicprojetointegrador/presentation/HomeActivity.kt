package com.g.vicprojetointegrador.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.presentation.adapter.PagerSectionAdapter
import com.g.vicprojetointegrador.presentation.viewmodel.MovieSharedViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout

/*
 * Activity on application launch
 * It holds two fragments/tabs: 1. DiscoverMovies 2. FavoriteMovies
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MovieSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val chipGroup = findViewById<ChipGroup>(R.id.cgGenreList)
        val pagerMovieList = findViewById<ViewPager2>(R.id.pagerMovieList)
        val tabPagerSection = findViewById<TabLayout>(R.id.tabPageSection)
        val searchView = findViewById<SearchView>(R.id.svSearchQuery)

        moviesViewModel = ViewModelProvider(this).get(MovieSharedViewModel::class.java)

        pagerMovieList.adapter = PagerSectionAdapter(supportFragmentManager, lifecycle)

        initializeTabPager(tabPagerSection, pagerMovieList)

        handleGenericError()

        // Adds chips to chipGroup dynamically
        moviesViewModel.genresLiveData.observe(this) { genres ->
            for (genre in genres) {
                val chip =
                    layoutInflater.inflate(R.layout.item_genre_home, chipGroup, false) as Chip
                chip.id = genre.id
                chip.text = genre.name
                chipGroup.addView(chip as View)
            }
        }

        // set chip group checked change listener
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
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

        searchView.setOnQueryTextFocusChangeListener { thisView, hasFocus ->
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

    private fun initializeTabPager(tabPagerSection: TabLayout, pagerMovieList: ViewPager2) {
        tabPagerSection.addTab(tabPagerSection.newTab().setText(R.string.all_movies))
        tabPagerSection.addTab(tabPagerSection.newTab().setText(R.string.favorite))

        tabPagerSection.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pagerMovieList.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        pagerMovieList.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabPagerSection.selectTab(tabPagerSection.getTabAt(position))
            }
        })

    }

}


