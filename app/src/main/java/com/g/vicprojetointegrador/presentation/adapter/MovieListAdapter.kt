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
import com.g.vicprojetointegrador.presentation.MovieClickListener
import com.g.vicprojetointegrador.utils.TMDBConstants
import com.g.vicprojetointegrador.utils.formatPercentage
import com.google.android.material.card.MaterialCardView
import com.like.LikeButton
import com.like.OnLikeListener


class MovieListAdapter(
    private val clickListener: MovieClickListener
) : ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffUtil()) {

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val cardMovie: MaterialCardView = itemView.findViewById(R.id.cardMovie)
        val ivMovie: ImageView = itemView.findViewById(R.id.itemMoviePoster)
        val tvVoteAverage: TextView = itemView.findViewById(R.id.tvVoteAverage)
        val btnFavorite: LikeButton = itemView.findViewById(R.id.btnLike)
    }

    class MovieDiffUtil :
        DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_card, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.tvTitle.text = movie.title
        holder.ivMovie.load("${TMDBConstants.IMAGE_URL}${movie.posterPath}")
        holder.tvVoteAverage.text = movie.voteAverage.formatPercentage()
        holder.cardMovie.setOnClickListener { clickListener.onMovieClick(movie) }
        holder.btnFavorite.isLiked = movie.isFavorited //set the initial state (filled/unfilled) of the button
        holder.btnFavorite.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
               clickListener.onFavoriteClick(movie)
            }

            override fun unLiked(likeButton: LikeButton?) {
                clickListener.onFavoriteClick(movie)
            }
        })
    }

}
