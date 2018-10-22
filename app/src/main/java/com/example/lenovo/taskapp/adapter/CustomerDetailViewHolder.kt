package com.example.lenovo.taskapp.adapter

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.lenovo.taskapp.MainActivity
import com.example.lenovo.taskapp.PayDialog
import com.example.lenovo.taskapp.TaskApp
import com.example.lenovo.taskapp.database.TaskDatabase
import com.example.lenovo.taskapp.database.entities.CustomerDetails
import com.example.lenovo.taskapp.databinding.CustomerRowBinding
import com.example.lenovo.taskapp.dependency.components.DaggerInjectActivityComponent
import com.example.lenovo.taskapp.newsale.ItemDialogFragment
import com.example.lenovo.taskapp.newsale.PayDialogListener
import com.example.lenovo.taskapp.newsale.SaleDetailHandler
import com.example.lenovo.taskapp.partially.PartiallyFragment
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class CustomerDetailViewHolder(val dataBinding: CustomerRowBinding) : RecyclerView.ViewHolder(dataBinding.root) {

    @Inject
    lateinit var database: TaskDatabase
    private lateinit var listener : PayDialogListener
    private lateinit var partiallyFragment: PartiallyFragment


    fun bindView(context : Context,customerDetails: CustomerDetails, position: Int) {
        partiallyFragment = PartiallyFragment()
        listener = partiallyFragment as PayDialogListener
        dataBinding.customerDetailsVm = customerDetails
        val depComponent = DaggerInjectActivityComponent.builder()
                .applicationComponent(TaskApp.applicationComponent)
                .build()
        depComponent.injectCustomerDetailViewHolder(this)

        dataBinding.saleDetailHandler = object : SaleDetailHandler{
            override fun onPayClick(view: View) {
                val manager = (context as AppCompatActivity).supportFragmentManager
                val ft = manager.beginTransaction()
                val prev = manager.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                val bundle = Bundle()
                bundle.putInt("position", position)
                bundle.putSerializable("CustomerDetails", customerDetails)
                ft.addToBackStack(null)
                val dialogFragment = PayDialog(partiallyFragment)
                dialogFragment.arguments = bundle
                dialogFragment.show(ft, "dialog")
            }

            override fun onDeleteClick(view: View) {
                database.customerDetailsDao().delete(customerDetails)
                Toast.makeText(context, "Data deleted successfully!", Toast.LENGTH_SHORT).show()
                listener.onDeleteSuccessfully()

            }

        }
    }

}