package com.example.mayank.kwizzapp.dependency.components

import com.example.lenovo.taskapp.PayDialog
import com.example.lenovo.taskapp.dependency.components.ApplicationComponent
import com.example.lenovo.taskapp.paid.PaidFragment
import com.example.lenovo.taskapp.partially.PartiallyFragment
import com.example.mayank.kwizzapp.dependency.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface InjectFragmentComponent {
    fun injectPartiallyFragment(partiallyFragment: PartiallyFragment)
    fun injectPaidFragment(paidFragment: PaidFragment)
    fun injectPayDialogFragment(payDialog: PayDialog)
}