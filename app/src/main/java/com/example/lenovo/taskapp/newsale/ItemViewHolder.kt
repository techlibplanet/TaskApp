package com.example.lenovo.taskapp.newsale

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.lenovo.taskapp.Constants
import com.example.lenovo.taskapp.databinding.ItemRowBinding
import com.example.lenovo.taskapp.viewmodel.ItemDetails

class ItemViewHolder(val dataBinding: ItemRowBinding) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bindView(context : Context,itemDetails: ItemDetails) {
        dataBinding.itemRowVm = itemDetails

        dataBinding.itemHandler = object : ItemHandler{
            override fun onClick(view: View) {
                Constants.itemList.add(itemDetails)
                Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show()
            }

        }


    }
}