package com.example.lenovo.taskapp.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField

class NewSaleVm : BaseObservable() {
    var name : String?= null
    val date : String?= null
    var paidAmount : String? = null
    var isFullyPaid : Boolean = false
    var itemName = ObservableField<String>()
    var rate = ObservableField<String>()
    var quantity = ObservableField<String>()
    var totalAmount = ObservableField<String>()

}