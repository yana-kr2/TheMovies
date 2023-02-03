package com.example.themovies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.themovies.databinding.ItemActorBinding
import com.example.themovies.model.cast.CastItem

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            ItemActorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CastAdapter.CastViewHolder, position: Int) {
        val currentItem = items[position]
        holder.binding.apply {
            actorName.text = currentItem.person.name
            actorPhoto.load(currentItem.person.image.original) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount(): Int = items.size


    inner class CastViewHolder(val binding: ItemActorBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<CastItem>() {
        override fun areItemsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
            return oldItem.person.id == newItem.person.id
        }

        override fun areContentsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
            return oldItem.person.id == newItem.person.id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var items: List<CastItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
}
