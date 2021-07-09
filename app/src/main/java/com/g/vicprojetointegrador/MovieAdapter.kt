package com.g.vicprojetointegrador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.card.MaterialCardView

class MovieAdapter(
    private val data: List<Movie>
): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val items: MutableList<MaterialCardView>

    init {
        this.items = ArrayList()
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val cardMovie : MaterialCardView = itemView.findViewById(R.id.cardMovie)
        val ivMovie : ImageView = itemView.findViewById(R.id.itemMoviePoster)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.tvTitle.text = data[position].title
        holder.ivMovie.load(data[position].posterUrl)

        items.add(holder.cardMovie)
    }

    override fun getItemCount(): Int {
       return data.size
    }
}