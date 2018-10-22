package com.example.lenovo.taskapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import com.example.lenovo.taskapp.adapter.CustomerDetailViewHolder
import com.example.lenovo.taskapp.database.TaskDatabase
import com.example.lenovo.taskapp.database.entities.CustomerDetails
import com.example.lenovo.taskapp.newsale.NewSaleActivity
import com.example.lenovo.taskapp.newsale.PayDialogListener
import com.example.lenovo.taskapp.partially.PartiallyFragment
import com.example.mayank.kwizzapp.dependency.components.DaggerInjectFragmentComponent
import org.jetbrains.anko.find
import javax.inject.Inject

class PayDialog() : DialogFragment(){

    private lateinit var alertDialog: AlertDialog
    private lateinit var partiallyFragment: PartiallyFragment
    private lateinit var listener: PayDialogListener
    @Inject
    lateinit var database: TaskDatabase
    private var position :Int = -1
    private lateinit var customerDetails: CustomerDetails
    private lateinit var amount : TextInputEditText
    private lateinit var checkBox : CheckBox

    @SuppressLint("ValidFragment")
    constructor(partiallyFragment: PartiallyFragment) : this() {
        this.partiallyFragment= partiallyFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val depComponent = DaggerInjectFragmentComponent.builder()
                .applicationComponent(TaskApp.applicationComponent)
                .build()
        depComponent.injectPayDialogFragment(this)

        position = arguments?.getInt("position")!!
        customerDetails = arguments?.getSerializable("CustomerDetails") as CustomerDetails
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        listener = partiallyFragment as PayDialogListener
        alertDialog = AlertDialog.Builder(activity!!).setTitle("Select Item")
                .setPositiveButton("Ok", null).setNegativeButton("Cancel", null).create()
        alertDialog.setOnShowListener { dialogInterface ->
            val okButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val cancelButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            okButton.setOnClickListener {
                if (view != null) {
                    val balanceAmount = customerDetails.totalAmount?.toInt()!! - amount.text.toString().toInt()
                    database.customerDetailsDao().update(balanceAmount.toString(), customerDetails.id)
                    Toast.makeText(activity, "Amount paid successfully", Toast.LENGTH_SHORT).show()
                    listener.onPaidSuccessfully(true)
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
            this.listener = partiallyFragment as PayDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("${partiallyFragment::class.java.simpleName} must implement OnCompleteListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pay_dialog_layout, container, false)
        amount = view.find(R.id.pay_amount)
        checkBox = view.find(R.id.isFullyPaid)

        amount.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val list = database.customerDetailsDao().getAllCustomerDetails()
                Log.d("TAG", "Text - $s")
                for (data in list){
                    checkBox.isChecked = data.balanceAmount == s.toString()
                }
            }

        })
        alertDialog.setView(view)
        return view
    }
}