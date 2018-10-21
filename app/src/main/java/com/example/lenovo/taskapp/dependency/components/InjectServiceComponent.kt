package com.example.mayank.kwizzapp.dependency.components

import com.example.lenovo.taskapp.dependency.components.ApplicationComponent
import com.example.mayank.kwizzapp.dependency.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface InjectServiceComponent {
}