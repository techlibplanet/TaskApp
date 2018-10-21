package com.example.lenovo.taskapp.paid

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.lenovo.taskapp.R
import com.example.lenovo.taskapp.TaskApp
import com.example.lenovo.taskapp.adapter.CustomerDetailAdapter
import com.example.lenovo.taskapp.database.TaskDatabase
import com.example.lenovo.taskapp.database.entities.CustomerDetails
import com.example.mayank.kwizzapp.dependency.components.DaggerInjectFragmentComponent
import org.jetbrains.anko.find
import javax.inject.Inject

class PaidFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var recyclerView: RecyclerView
    val adapter: CustomerDetailAdapter by lazy { CustomerDetailAdapter() }
    private lateinit var modelList : MutableList<CustomerDetails>
    @Inject
    lateinit var database: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        modelList = mutableListOf()
        val depComponent = DaggerInjectFragmentComponent.builder()
            .applicationComponent(TaskApp.applicationComponent)
            .build()
        depComponent.injectPaidFragment(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_paid, container, false)
        recyclerView = view.find(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        setItems()
        return view
    }

    private fun setItems() {
        val list = mutableListOf<CustomerDetails>()

        val customerDetails = CustomerDetails()
        customerDetails.name = "Mayank Sharma"
        customerDetails.balanceAmount = "20"
        customerDetails.totalAmount = "100"
        customerDetails.date = "12-10-18"
        list.add(customerDetails)

        val customerDetail2 = CustomerDetails()
        customerDetail2.name = "Mayank Sharma"
        customerDetail2.balanceAmount = "20"
        customerDetail2.totalAmount = "100"
        customerDetail2.date = "12-10-18"
        list.add(customerDetail2)

        setRecyclerViewAdapter(list)
    }

    private fun setRecyclerViewAdapter(list: List<CustomerDetails>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

    }
}
