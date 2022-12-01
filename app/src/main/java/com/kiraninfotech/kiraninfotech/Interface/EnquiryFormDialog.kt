package com.kiraninfotech.kiraninfotech.Interface

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.widget.Toast
import com.kiraninfotech.kiraninfotech.Model.EnquiryModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.enquiry_form_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

interface EnquiryFormDialog {

    companion object {

        @SuppressLint("SetTextI18n")
         fun openEnquiryDialog(activity: Activity, productName: String) {

            val calendar = Calendar.getInstance()

            Dialog(activity).apply {
                setContentView(R.layout.enquiry_form_dialog)

                this.enquiryProduct.setText(productName)

                var enquiryDate = this.enquiryDate
                enquiryDate.setText(
                    "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${
                        calendar.get(
                            Calendar.YEAR
                        )
                    }"
                )

                this.changeDateBtn.setOnClickListener {
                    val datePicker = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        DatePickerDialog(
                            context,
                            { p0, p1, p2, p3 -> enquiryDate.setText("$p3/${p2 + 1}/$p1") },
                            calendar.get(
                                Calendar.YEAR
                            ),
                            calendar.get(Calendar.MONTH),
                            calendar.get(
                                Calendar.DAY_OF_MONTH
                            )
                        ).show()
                    } else {

                    }
                }

                this.btnSubmitEnquiry.setOnClickListener {

                    if (this.enquiryCompany.text.toString() == "" || this.enquiryContact.text.toString() == "" || this.enquiryMobileNo.text.toString() == "") {
                        Toast.makeText(
                            activity,
                            "Please fill mandatory details",
                            Toast.LENGTH_SHORT
                        )
                    } else {

                        val enquiryData = EnquiryModel(
                            this.enquiryCompany.text.toString(),
                            this.enquiryContact.text.toString(),
                            this.enquiryMobileNo.text.toString(),
                            this.enquiryProduct.text.toString(),
                            this.enquiryRemarks.text.toString(),
                            this.enquiryDate.text.toString())


                        ApiInterface.create().addEnquiry(enquiryData).enqueue(object : Callback<EnquiryModel>{
                            override fun onResponse(
                                call: Call<EnquiryModel>,
                                response: Response<EnquiryModel>
                            ) {
                                Toast.makeText(activity, " \uD83D\uDE4F Thank You for showing interest on us we will get back to you soon", Toast.LENGTH_SHORT).show()
                                dismiss()
                            }

                            override fun onFailure(call: Call<EnquiryModel>, t: Throwable) {
                                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                                dismiss()
                            }

                        })
                    }

                }


            }.show()
        }

    }
}