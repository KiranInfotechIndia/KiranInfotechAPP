package com.kiraninfotech.kiraninfotech.Interface

import android.app.Activity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.bottom_sheet_dialog.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog.btnCloseDialog
import kotlinx.android.synthetic.main.bottom_sheet_dialog_aws.*
import java.text.DecimalFormat

interface AwsPlanBottomSheetDialog {

    companion object{

        fun openAwsPricingDialog(activity: Activity, awsPlanName : String, awsPlanGST : Int, awsPlanMonthTaxableValue : Int){
            BottomSheetDialog(activity).apply {
                setContentView(R.layout.bottom_sheet_dialog_aws)

                this.awsPlanName.text = awsPlanName
                val decimalFormat = DecimalFormat("0.00")

                this.awsGSTPercentage.text = "GST @ $awsPlanGST %"

                this.awsQuarterlyTaxableValue.text = (decimalFormat.format(awsPlanMonthTaxableValue * 3).toString())
                this.awsHalfYearlyTaxableValue.text = (decimalFormat.format(awsPlanMonthTaxableValue * 6).toString())
                this.awsYearlyTaxableValue.text = (decimalFormat.format(awsPlanMonthTaxableValue * 12).toString())

                this.awsQuarterlyGSTValue.text = (decimalFormat.format(((awsPlanMonthTaxableValue * 3) * awsPlanGST)/100)).toString()
                this.awsHalfYearlyGSTValue.text = (decimalFormat.format(((awsPlanMonthTaxableValue * 6) * awsPlanGST)/100)).toString()
                this.awsYearlyGSTValue.text = (decimalFormat.format(((awsPlanMonthTaxableValue * 12) * awsPlanGST)/100)).toString()

                this.awsQuarterlyTotalValue.text = (decimalFormat.format((awsPlanMonthTaxableValue * 3) +  (((awsPlanMonthTaxableValue * 3) * awsPlanGST)/100))).toString()
                this.awsHalfYearlyTotalValue.text = (decimalFormat.format((awsPlanMonthTaxableValue * 6) +  (((awsPlanMonthTaxableValue * 6) * awsPlanGST)/100))).toString()
                this.awsYearlyTotalValue.text = (decimalFormat.format((awsPlanMonthTaxableValue * 12) +  (((awsPlanMonthTaxableValue * 12) * awsPlanGST)/100))).toString()

                this.btnCloseDialog.setOnClickListener {
                    dismiss()
                }
            }.show()
        }
    }

}

