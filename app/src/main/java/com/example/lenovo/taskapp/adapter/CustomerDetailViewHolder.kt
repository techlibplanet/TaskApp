package com.example.lenovo.taskapp.adapter

import android.support.v7.widget.RecyclerView
import com.example.lenovo.taskapp.database.entities.CustomerDetails
import com.example.lenovo.taskapp.databinding.CustomerRowBinding

class CustomerDetailViewHolder(val dataBinding: CustomerRowBinding) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bindView(customerDetails: CustomerDetails) {
        dataBinding.customerDetailsVm = customerDetails
    }
}