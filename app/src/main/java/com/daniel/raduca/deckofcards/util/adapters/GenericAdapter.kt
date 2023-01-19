package com.daniel.raduca.deckofcards.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

interface BindingHandler<T : Any> {
    fun bindData(item: T, view: View)
}

class GenericAdapter<T : Any>(
    @LayoutRes val layoutId: Int,
    private val bindingHandler: BindingHandler<T>,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, GenericAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun <T : Any> bind(
            item: T,
            bindingHandler: BindingHandler<T>
        ) = bindingHandler.bindData(item, view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, bindingHandler)
    }
}
