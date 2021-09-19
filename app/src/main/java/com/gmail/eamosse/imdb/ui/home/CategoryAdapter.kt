package com.gmail.eamosse.imdb.ui.home

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.imdb.databinding.CategoryListItemBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


class CategoryAdapter(private val items: List<Category>, private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    companion object MyDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class ViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            binding.item = item
        }
    }

    class OnClickListener(val clickListener: (meme: Category) -> Unit) {
        fun onClick(meme: Category) = clickListener(meme)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(CategoryListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListener.onClick(items[position])
        }
        holder.bind(items[position])
    }



}