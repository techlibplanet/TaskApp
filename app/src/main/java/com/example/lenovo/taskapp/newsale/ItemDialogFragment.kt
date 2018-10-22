package com.example.lenovo.taskapp.newsale

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lenovo.taskapp.Constants
import com.example.lenovo.taskapp.R
import com.example.lenovo.taskapp.viewmodel.ItemDetails
import org.jetbrains.anko.find

class ItemDialogFragment() : DialogFragment() {

    private lateinit var alertDialog: AlertDialog
    private lateinit var newSaleActivity: NewSaleActivity
    val adapter: ItemDetailAdapter by lazy { ItemDetailAdapter() }
    private lateinit var listener: ItemDialogListener

    @SuppressLint("ValidFragment")
    constructor(newSaleActivity: NewSaleActivity) : this() {
        this.newSaleActivity = newSaleActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        listener = newSaleActivity as ItemDialogListener
        alertDialog = AlertDialog.Builder(activity!!).setTitle("Select Item")
            .setPositiveButton("Ok", null).setNegativeButton("Cancel", null).create()
        alertDialog.setOnShowListener { dialogInterface ->
            val okButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val cancelButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            okButton.setOnClickListener {
                if (view != null) {
                    listener.onItemAdd(Constants.itemList)
                    dialogInterface.dismiss()
                }
            }
            cancelButton.setOnClickListener {
                dialogInterface.dismiss()
            }
        }
        return alertDialog
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            // Registering listener
            this.listener = newSaleActivity as ItemDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${NewSaleActivity::class.java.simpleName} must implement OnCompleteListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.item_list_dialog, container, false)
        val recyclerView = view.find<RecyclerView>(R.id.dialogRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        alertDialog.setView(view)
        setItems()
        return view
    }

    private fun setItems() {
        val itemList = mutableListOf<ItemDetails>()
        val itemData1 = ItemDetails("Samsung Tab A", "25000", "1", "25000")
        val itemData2 = ItemDetails("Nokia 6.1 Plus", "30000", "1", "30000")
        val itemData3 = ItemDetails("Poco F1", "28999", "1", "28999")
        itemList.add(itemData1)
        itemList.add(itemData2)
        itemList.add(itemData3)
        setRecyclerViewAdapter(itemList)
    }

    private fun setRecyclerViewAdapter(list: List<ItemDetails>) {
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
}