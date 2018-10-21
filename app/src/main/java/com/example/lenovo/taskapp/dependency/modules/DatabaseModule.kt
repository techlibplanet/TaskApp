package com.example.lenovo.taskapp.dependency.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.lenovo.taskapp.database.TaskDatabase
import com.example.lenovo.taskapp.dependency.qualifiers.ApplicationContextQualifier
import com.example.mayank.kwizzapp.dependency.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(AppContextModule::class))
class DatabaseModule {
    @Provides
    @ApplicationScope
    fun mfExpertLmsDatabase(@ApplicationContextQualifier context: Context): TaskDatabase {
        return Room.databaseBuilder(context, TaskDatabase::class.java, "task_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}