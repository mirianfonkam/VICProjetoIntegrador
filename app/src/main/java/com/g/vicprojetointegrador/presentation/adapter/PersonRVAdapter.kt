package com.g.vicprojetointegrador.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.g.vicprojetointegrador.R
import com.g.vicprojetointegrador.data.model.Cast
import com.g.vicprojetointegrador.utils.TMDBConstants

class PersonRVAdapter(
    private val data: List<Cast>
): RecyclerView.Adapter<PersonRVAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvPersonRole : TextView = itemView.findViewById(R.id.tvPersonRole)
        val tvPersonName : TextView = itemView.findViewById(R.id.tvPersonName)
        val ivPersonProfile : ImageView = itemView.findViewById(R.id.ivPersonProfile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonRVAdapter.ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_person, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: PersonRVAdapter.ViewHolder, position: Int) {
        val cast = data[position]
        with(
            holder,
            {
                tvPersonName.text = cast.actorName
                tvPersonRole.text = cast.characterName
                ivPersonProfile.load(cast.profileImageUrl){
                    crossfade(true)
                    transformations(CircleCropTransformation())
                    fallback(R.drawable.ic_profile_no_image)
                }
            },
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }
}