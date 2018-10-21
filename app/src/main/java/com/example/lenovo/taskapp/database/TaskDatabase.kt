package com.example.lenovo.taskapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.lenovo.taskapp.database.dao.CustomerDetailsDao
import com.example.lenovo.taskapp.database.entities.CustomerDetails

@Database(entities = [CustomerDetails::class], version = 2)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun customerDetailsDao() : CustomerDetailsDao
}