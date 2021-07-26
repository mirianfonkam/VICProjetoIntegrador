package com.g.vicprojetointegrador.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

// A simple ViewPager adapter class for paging through fragments
class PagerSectionAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
            : FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DiscoverMoviesFragment()
            else -> FavoritesFragment()
        }
    }

}

//class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//    //viewPager2
//    override fun getCount(): Int {
//        return 2
//    }
//
//    override fun getItem(position: Int): Fragment {
//        return when (position){
//            1 -> SecondFragment()
//            else -> FirstFragment()
//        }
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return when (position) {
//            1 -> "Segundo"
//            else -> "Primeiro"
//        }
//    }
//
//}