package com.g.vicprojetointegrador.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.g.vicprojetointegrador.presentation.DiscoverMoviesFragment
import com.g.vicprojetointegrador.presentation.FavoriteMoviesFragment

// A simple ViewPager adapter class for paging through a small, fixed number of fragments
class PagerSectionAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
            : FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DiscoverMoviesFragment()
            else -> FavoriteMoviesFragment()
        }
    }
}