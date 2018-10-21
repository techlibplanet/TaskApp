package com.example.lenovo.taskapp.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.lenovo.taskapp.database.entities.CustomerDetails

@Dao
interface CustomerDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(district: CustomerDetails)

    @Query("SELECT * FROM customer_details")
    fun getAllCustomerDetails(): List<CustomerDetails>

    @Query("SELECT name FROM customer_details")
    fun getCustomerNames() : List<String>

    @Query("SELECT id FROM customer_details")
    fun getCustomerIds() : List<Int>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(district: List<CustomerDetails>?)
}