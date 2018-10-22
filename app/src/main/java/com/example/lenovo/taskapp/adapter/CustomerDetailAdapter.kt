package com.example.lenovo.taskapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.lenovo.taskapp.database.entities.CustomerDetails
import com.example.lenovo.taskapp.databinding.CustomerRowBinding

class CustomerDetailAdapter: RecyclerView.Adapter<CustomerDetailViewHolder>() {
    var items: List<CustomerDetails> = emptyList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerDetailViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val dataBinding = CustomerRowBinding.inflate(inflater, parent, false)
        return CustomerDetailViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomerDetailViewHolder, position: Int) {
        val customerDetails = items[position]
        holder.bindView(context,customerDetails, position)
    }
}