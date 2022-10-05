package com.csc2002s.assgnmnts.apps.alphabetapplication.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.csc2002s.assgnmnts.apps.alphabetapplication.CollectionFragment
import com.csc2002s.assgnmnts.apps.alphabetapplication.adapters.CollectionAdapter.CollectionViewHolder
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.UnsplashPhoto
import com.csc2002s.assgnmnts.apps.alphabetapplication.databinding.ListItemPhotoBinding

/**
 * Adapter for the [RecyclerView] in [CollectionFragment].
 */

class CollectionAdapter : PagingDataAdapter<UnsplashPhoto, CollectionViewHolder>(CollectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(
            ListItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class CollectionViewHolder(
        private val binding: ListItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.photo?.let { photo ->
                    val uri = Uri.parse(photo.user.attributionUrl)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    view.context.startActivity(intent)
                }
            }
        }

        fun bind(item: UnsplashPhoto) {
            binding.apply {
                photo = item
                executePendingBindings()
            }
        }
    }
}

private class CollectionDiffCallback : DiffUtil.ItemCallback<UnsplashPhoto>() {
    override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem == newItem
    }
}
