package com.g.vicprojetointegrador.presentation

import com.g.vicprojetointegrador.data.model.Movie

interface MovieClickListener {

    fun onMovieClick(movie: Movie)

    fun onFavoriteClick(movie: Movie)

}
