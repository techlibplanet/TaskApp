package com.example.lenovo.taskapp.newsale

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.lenovo.taskapp.databinding.ItemRowBinding
import com.example.lenovo.taskapp.viewmodel.ItemDetails

class ItemDetailAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    var items: List<ItemDetails> = emptyList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val dataBinding = ItemRowBinding.inflate(inflater, parent, false)
        return ItemViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemDetails = items[position]
        holder.bindView(context,itemDetails, position)
    }
}