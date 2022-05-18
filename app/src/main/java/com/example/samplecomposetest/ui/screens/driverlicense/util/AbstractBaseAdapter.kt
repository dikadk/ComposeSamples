package com.example.samplecomposetest.ui.screens.driverlicense.util

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractBaseAdapter<T, VH : RecyclerView.ViewHolder?>(context: Context?) : BaseDataAdapter<T, VH>() {

    protected val inflater: LayoutInflater = LayoutInflater.from(context)

    abstract fun doBind(holder: VH, item: T, position: Int)

    open fun doBind(holder: VH, item: T, position: Int, payloads: List<Any>) {
        doBind(holder, item, position)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        doBind(holder, getItem(position), position)
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: List<Any>) {
        doBind(holder, getItem(position), position, payloads)
    }
}
