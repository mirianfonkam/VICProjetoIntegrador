package com.g.vicprojetointegrador.presentation

import com.g.vicprojetointegrador.data.model.Movie

//This interface is used when clicking on a movie
interface MovieClickListener {
    //This is for when when clicking on a movie to view details
    fun onMovieClick(movie: Movie)
    //This is for when when clicking on the heart icon to favorite a movie
    fun favoriteClicked(movie: Movie)
}
