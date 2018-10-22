package com.example.lenovo.taskapp.newsale

import android.app.DatePickerDialog
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import com.example.lenovo.taskapp.*
import com.example.lenovo.taskapp.database.TaskDatabase
import com.example.lenovo.taskapp.database.entities.CustomerDetails
import com.example.lenovo.taskapp.databinding.NewSaleBinding
import com.example.lenovo.taskapp.dependency.components.DaggerInjectActivityComponent
import com.example.lenovo.taskapp.viewmodel.ItemDetails
import com.example.lenovo.taskapp.viewmodel.NewSaleVm
import kotlinx.android.synthetic.main.new_sale_layout.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import java.util.*
import javax.inject.Inject

class NewSaleActivity : AppCompatActivity(), ItemDialogListener, View.OnClickListener {

    private lateinit var toolbar : Toolbar
    private lateinit var recyclerView: RecyclerView
    val adapter: ItemDetailAdapter by lazy { ItemDetailAdapter() }
    private lateinit var modelList : MutableList<CustomerDetails>
    private var total : Int = 0
    private var quantity : Int = 0
    private lateinit var databinding : NewSaleBinding
    private lateinit var newSaleVm : NewSaleVm
    private lateinit var calender : Calendar
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private lateinit var dialog: DatePickerDialog
    @Inject
    lateinit var database: TaskDatabase
    private val CLICKABLES = intArrayOf(R.id.fabAddItem, R.id.buttonDone)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this,R.layout.activity_new_sale)
        newSaleVm = NewSaleVm()
        databinding.newSaleVm = newSaleVm

        modelList = mutableListOf()
        toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val depComponent = DaggerInjectActivityComponent.builder()
                .applicationComponent(TaskApp.applicationComponent)
                .build()
        depComponent.injectNewSaleActivity(this)

        showTotalViews(true)

        // set date click listener
        inputDate.setOnClickListener{
            calender = Calendar.getInstance()

            year = calender.get(Calendar.YEAR)
            month = calender.get(Calendar.MONTH)
            day = calender.get(Calendar.DAY_OF_MONTH)

            dialog = DatePickerDialog(this,
                    mDateSetListener, year, month, day)
            dialog.show()
        }


        for (id in CLICKABLES){
            find<Button>(id).setOnClickListener(this)
        }
        recyclerView = find(R.id.newSaleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        setItem()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fabAddItem -> addItems()
            R.id.buttonDone -> saveSale()
        }
    }

    private fun saveSale() {
        if (validate()){
            val customerDetails = CustomerDetails()
            customerDetails.name = databinding.newSaleVm?.name
            customerDetails.date = databinding.newSaleVm?.date?.get()
            customerDetails.totalAmount = databinding.newSaleVm?.totalAmount?.get()
            val balanceAmount = customerDetails.totalAmount?.toInt()!! - databinding.newSaleVm?.paidAmount?.toInt()!!
            customerDetails.balanceAmount = balanceAmount.toString()
            if (customerDetails.totalAmount == null){
                customerDetails.totalAmount = 0.toString()
            }
            customerDetails.isFullyPaid = checkBox.isChecked

            database.customerDetailsDao().insert(customerDetails)

            Constants.itemList.clear()
            startActivity<MainActivity>()
        }
    }

    private fun validate(): Boolean {
        if (databinding.newSaleVm?.name.isNullOrBlank()){
            inputLayoutName.error = "Enter Valid Name"
            return false
        }else{
            inputLayoutName.error = null
        }
        if (databinding.newSaleVm?.date?.get().isNullOrBlank()){
            inputLayoutDate.error = "Enter valid date"
            return false
        }else{
            inputLayoutDate.error = null
        }
        if (databinding.newSaleVm?.paidAmount.isNullOrBlank()){
            inputLayoutPaidAmount.error = "Enter paid amount"
            return false
        }else{
            inputLayoutPaidAmount.error = null
        }
        if (databinding.newSaleVm?.paidAmount?.toInt() ==0){
            inputLayoutPaidAmount.error = "Enter paid amount"
            return false
        }else{
            inputLayoutPaidAmount.error = null
        }

        return true
    }

    // Adding items to recycler view
    private fun addItems() {
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

    // Date listener
    private val mDateSetListener = DatePickerDialog.OnDateSetListener { view, y, m, d ->
        year = y
        month = m + 1
        day = d
        val date = StringBuilder().append(day).append("-").append(month)
                .append("-").append(year).append("").toString()
        inputDate.setText(date)
    }

    // Setting items
    private fun setItem(){
        val itemDetails = ItemDetails("Item","Rate","Qty","Total")
        Constants.itemList.add(itemDetails)
        setRecyclerViewAdapter(Constants.itemList)

    }


    private fun setRecyclerViewAdapter(list: List<ItemDetails>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

    // After adding item in dialog fragment changing view
    override fun onItemAdd(itemDetailList: MutableList<ItemDetails>) {
        for (data in itemDetailList){
            if (data.totalAmount != "Total"){
                total += data.totalAmount.toInt()
                quantity+= data.quantity.toInt()
            }
            showTotalViews(false)
            databinding.newSaleVm?.itemName?.set("Sub Total")
            databinding.newSaleVm?.quantity?.set(quantity.toString())
            databinding.newSaleVm?.rate?.set("")
            databinding.newSaleVm?.totalAmount?.set(total.toString())
        }
        setRecyclerViewAdapter(itemDetailList)
    }

    private fun showTotalViews(disable : Boolean){
        databinding.totalViews = disable
    }


}
