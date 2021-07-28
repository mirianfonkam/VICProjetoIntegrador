package com.g.vicprojetointegrador.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.g.vicprojetointegrador.R


/*
 * Favorite movies tab pager
 * movies that the user has tapped the like button
 */
class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesFragment()

    }
}