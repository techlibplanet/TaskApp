package com.example.lenovo.taskapp.newsale

import android.view.View
import com.example.lenovo.taskapp.viewmodel.ItemDetails

interface ItemDialogListener {
    fun onItemAdd(itemDetails: MutableList<ItemDetails>)
}

interface ItemHandler{
    fun onClick(view:View)
}