package com.example.picturesum.display

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.picturesum.data.ImageDetails
import com.example.picturesum.databinding.GridViewItemBinding

class PhotoGridAdapter :
    ListAdapter<ImageDetails, PhotoGridAdapter.ImageDetailsViewHolder>(DiffCallback) {
    class ImageDetailsViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageDetails: ImageDetails) {
            binding.property = imageDetails
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ImageDetails>() {
        override fun areItemsTheSame(oldItem: ImageDetails, newItem: ImageDetails): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ImageDetails, newItem: ImageDetails): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.ImageDetailsViewHolder  {
        return ImageDetailsViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageDetailsViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }
}