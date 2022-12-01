package com.kiraninfotech.kiraninfotech.Interface

import android.app.Activity
import android.content.Context
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*

interface BottomSheetDialog {

    companion object{

        fun openBottomSheetDialog(activity: Activity, itemName :String, itemPrice: String, itemDesc : ArrayList<String>){

            BottomSheetDialog(activity).apply {
                setContentView(R.layout.bottom_sheet_dialog)
                this.itemName.text = itemName
                this.itemPrice.text = itemPrice
                this.itemDescription.adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, itemDesc)
                this.btnCloseDialog.setOnClickListener {
                    dismiss()
                }
            }.show()
        }
    }
}