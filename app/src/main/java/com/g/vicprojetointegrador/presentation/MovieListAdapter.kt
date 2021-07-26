package com.g.vicprojetointegrador.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Movie
import com.g.vicprojetointegrador.data.model.TMDBConstants
import com.google.android.material.card.MaterialCardView

class MovieListAdapter(
    private val data: List<Movie>
): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val items = mutableListOf<MaterialCardView>()

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val cardMovie : MaterialCardView = itemView.findViewById(R.id.cardMovie)
        val ivMovie : ImageView = itemView.findViewById(R.id.itemMoviePoster)
        val tvVoteAverage : TextView = itemView.findViewById(R.id.tvVoteAverage)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = data[position]
        holder.tvTitle.text = movie.title
        holder.ivMovie.load("${TMDBConstants.IMAGE_URL}${movie.posterPath}")
        holder.tvVoteAverage.text = "${(movie.voteAverage*10).toInt()}%" //Move calc to util

        items.add(holder.cardMovie)
    }

    override fun getItemCount(): Int {
       return data.size
    }
}
