package com.gmail.eamosse.imdb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.MovieList
import com.gmail.eamosse.imdb.databinding.MovieListItemBinding

class MovieListAdapter (private val items: List<MovieList>, private val onClickListener: MovieListAdapter.OnClickListener) :

    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

        inner class ViewHolder(private val binding: MovieListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(item: MovieList) {
                binding.item = item
            }
        }

        class OnClickListener(val clickListener: (meme: MovieList) -> Unit) {
            fun onClick(meme: MovieList) = clickListener(meme)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ViewHolder(MovieListItemBinding.inflate(inflater, parent, false))
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(items[position])
            }
            holder.bind(items[position])
        }
}