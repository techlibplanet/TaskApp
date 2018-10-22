package com.example.lenovo.taskapp.dependency.components

import com.example.lenovo.taskapp.MainActivity
import com.example.lenovo.taskapp.adapter.CustomerDetailViewHolder
import com.example.lenovo.taskapp.newsale.NewSaleActivity
import com.example.mayank.kwizzapp.dependency.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface InjectActivityComponent {
    fun injectMainActivity(mainActivity: MainActivity)
    fun injectNewSaleActivity(newSaleActivity: NewSaleActivity)
    fun injectCustomerDetailViewHolder(customerDetailViewHolder: CustomerDetailViewHolder)
}
