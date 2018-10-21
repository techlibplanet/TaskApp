package com.example.lenovo.taskapp.dependency.components

import android.content.Context
import com.example.lenovo.taskapp.database.TaskDatabase
import com.example.lenovo.taskapp.dependency.modules.AppContextModule
import com.example.lenovo.taskapp.dependency.modules.DatabaseModule
import com.example.lenovo.taskapp.dependency.qualifiers.ApplicationContextQualifier
import com.example.mayank.kwizzapp.dependency.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(AppContextModule::class, DatabaseModule::class))
interface ApplicationComponent {

    @ApplicationContextQualifier
    fun getAppContext(): Context

    fun getDatabase(): TaskDatabase


}