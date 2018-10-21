package com.example.lenovo.taskapp.dependency.components

import com.example.lenovo.taskapp.MainActivity
import com.example.mayank.kwizzapp.dependency.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface InjectActivityComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}
