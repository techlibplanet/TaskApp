package com.example.lenovo.taskapp

import android.app.Application
import com.example.lenovo.taskapp.dependency.components.ApplicationComponent
import com.example.lenovo.taskapp.dependency.components.DaggerApplicationComponent
import com.example.lenovo.taskapp.dependency.modules.AppContextModule

class TaskApp : Application(){

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .appContextModule(AppContextModule(applicationContext))
            .build()
    }
}