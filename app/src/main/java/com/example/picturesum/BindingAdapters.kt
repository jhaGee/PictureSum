package com.example.picturesum

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.picturesum.data.ImageDetails
import com.example.picturesum.display.ImageApiStatus
import com.example.picturesum.display.PhotoGridAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ImageDetails>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, id: String?) {
    val url= "https://picsum.photos/300/300?image=$id"
    url?.let {
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Log.i("URI", imgUri.toString())
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}
@BindingAdapter("authorName")
fun bindText(textView: TextView,author:String?){
    textView.text = author
}
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: ImageApiStatus?) {
    when (status) {
        ImageApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ImageApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ImageApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}