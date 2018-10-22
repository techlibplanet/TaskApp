package com.example.lenovo.taskapp.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField

class NewSaleVm : BaseObservable() {
    var name : String?= null
    val date =ObservableField<String>()
    var paidAmount : String? = null
    var isFullyPaid = ObservableField<Boolean>()
    var itemName = ObservableField<String>()
    var rate = ObservableField<String>()
    var quantity = ObservableField<String>()
    var totalAmount = ObservableField<String>()

}