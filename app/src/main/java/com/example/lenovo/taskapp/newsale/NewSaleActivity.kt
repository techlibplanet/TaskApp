package com.example.lenovo.taskapp.newsale

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.lenovo.taskapp.Constants
import com.example.lenovo.taskapp.R
import com.example.lenovo.taskapp.database.entities.CustomerDetails
import com.example.lenovo.taskapp.databinding.NewSaleBinding
import com.example.lenovo.taskapp.viewmodel.ItemDetails
import kotlinx.android.synthetic.main.new_sale_layout.*
import org.jetbrains.anko.find

class NewSaleActivity : AppCompatActivity(), ItemDialogListener {

    private lateinit var toolbar : Toolbar
    private lateinit var recyclerView: RecyclerView
    val adapter: ItemDetailAdapter by lazy { ItemDetailAdapter() }
    private lateinit var modelList : MutableList<CustomerDetails>
    private lateinit var fabAddItem : FloatingActionButton
    private var total : Int = 0
    private var quantity : Int = 0
    private lateinit var databinding : NewSaleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this,R.layout.activity_new_sale)

        modelList = mutableListOf()
        toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        fabAddItem = find(R.id.fabAddItem)

        fabAddItem.setOnClickListener{
            total = 0
            quantity = 0
            val ft = supportFragmentManager.beginTransaction()
            val prev = supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            val dialogFragment = ItemDialogFragment(this)
            dialogFragment.show(ft, "dialog")
        }


        recyclerView = find(R.id.newSaleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        setItem()
    }

    private fun setItem(){
        val itemDetails = ItemDetails("Item Name","Rate","Qty","Total")
        Constants.itemList.add(itemDetails)
        setRecyclerViewAdapter(Constants.itemList)

    }


    private fun setRecyclerViewAdapter(list: List<ItemDetails>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    override fun onItemAdd(itemDetailList: MutableList<ItemDetails>) {
        for (data in itemDetailList){
            if (data.itemName != "Item Name"){
                total += data.totalAmount.toInt()
                quantity+= data.quantity.toInt()
            }
            databinding.newSaleVm?.itemName?.set("Sub Total")
            databinding.newSaleVm?.quantity?.set(quantity.toString())
            databinding.newSaleVm?.rate?.set("")
            databinding.newSaleVm?.totalAmount?.set(total.toString())
        }
        setRecyclerViewAdapter(itemDetailList)
    }
}
