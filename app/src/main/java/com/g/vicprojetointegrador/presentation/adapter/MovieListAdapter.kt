package com.g.vicprojetointegrador.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.model.TMDBConstants
import com.google.android.material.card.MaterialCardView

class MovieDiffUtil :
    DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}

class MovieListAdapter(
    private val clickListener: MovieClickListener
): ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffUtil()) {

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val cardMovie : MaterialCardView = itemView.findViewById(R.id.cardMovie)
        val ivMovie : ImageView = itemView.findViewById(R.id.itemMoviePoster)
        val tvVoteAverage : TextView = itemView.findViewById(R.id.tvVoteAverage)
        val btnFavorite : View = itemView.findViewById(R.id.btnFavorite)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.tvTitle.text = movie.title
        holder.ivMovie.load("${TMDBConstants.IMAGE_URL}${movie.posterPath}")
        holder.tvVoteAverage.text = "${(movie.voteAverage*10).toInt()}%" //Move calc to util
        holder.btnFavorite.setOnClickListener { clickListener.favoriteClicked(movie) }
        holder.cardMovie.setOnClickListener { clickListener.onMovieClick(movie) }
    }

    //This interface is used when clicking on a movie poster
    interface MovieClickListener {
        //This is for when when clicking on a movie to view details
        fun onMovieClick(movie: Movie)
        //This is for when when clicking on the heart icon to favorite a movie
        fun favoriteClicked(movie: Movie)
    }


}
