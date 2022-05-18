package com.example.samplecomposetest.ui.screens.driverlicense.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseBindingAdapter<T, VH : RecyclerView.ViewHolder>(context: Context?) : AbstractBaseAdapter<T, VH>(context) {

    abstract fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createViewHolder(inflater, parent, viewType)
    }

    abstract class BindingVH <B: ViewBinding>(binding: B) : RecyclerView.ViewHolder(binding.root)
}
