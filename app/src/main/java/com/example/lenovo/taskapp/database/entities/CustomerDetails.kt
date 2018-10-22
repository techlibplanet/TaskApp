package com.example.lenovo.taskapp.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "customer_details")
class CustomerDetails : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "date")
    var date: String? = null

    @ColumnInfo(name = "totalAmount")
    var totalAmount: String? = null

    @ColumnInfo(name = "balanceAmount")
    var balanceAmount: String? = null


    @ColumnInfo(name = "isFullyPaid")
    var isFullyPaid: Boolean = false








}