package com.daniel.raduca.deckofcards.util.adapters

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.daniel.raduca.deckofcards.R

/**
 * Sets data list to RecyclerView
 */
@BindingAdapter("data")
fun <T : Any> bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<T>
) {
    val adapter = recyclerView.adapter as GenericAdapter<T>
    adapter.submitList(data)
}

/**
 * Sets view visibility through data binding
 */
@BindingAdapter("visibility")
fun bindVisibility(
    view: View,
    isVisible: Boolean
) {
    if (isVisible) view.visibility = View.VISIBLE else view.visibility = View.GONE
}

/**
 * Uses Coil library to download and set the image
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_stop_sign)
        }
    }
}