package com.example.lenovo.taskapp.dependency.modules

import android.content.Context
import com.example.lenovo.taskapp.dependency.qualifiers.ApplicationContextQualifier
import com.example.mayank.kwizzapp.dependency.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(@ApplicationContextQualifier val context: Context) {
    @Provides
    @ApplicationScope
    @ApplicationContextQualifier
    fun provideContext(): Context {
        return context
    }
}