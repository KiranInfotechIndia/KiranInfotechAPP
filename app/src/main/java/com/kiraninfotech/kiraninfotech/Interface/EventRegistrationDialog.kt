package com.kiraninfotech.kiraninfotech.Interface

import android.app.Activity
import android.app.Dialog
import android.widget.Toast
import com.kiraninfotech.kiraninfotech.Model.EventCustomerRegistrationModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.event_registration_dialog.*
import kotlinx.android.synthetic.main.student_registration_dialog.btnSubmitRegistration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface EventRegistrationDialog {


    companion object{

        fun openEventRegistrationDialog(activity: Activity, eventID : String){

            Dialog(activity).apply {
                setContentView(R.layout.event_registration_dialog)

                this.btnSubmitRegistration.setOnClickListener {
                    if (this.customerName.text.toString() == "" || this.customerMobile.text.toString() == "" || this.customerCompany.text.toString() == "" || this.customerEmail.text.toString() == ""){
                        Toast.makeText(activity, "Please fill required Details", Toast.LENGTH_SHORT).show()
                    }else{

                        val eventCustomerData = EventCustomerRegistrationModel(
                            this.customerName.text.toString(),
                            this.customerCompany.text.toString(),
                            this.customerMobile.text.toString(),
                            this.customerEmail.text.toString(),
                            eventID
                        )


                        ApiInterface.create().addEventRegistration(eventCustomerData).enqueue(object : Callback<EventCustomerRegistrationModel>{
                            override fun onResponse(
                                call: Call<EventCustomerRegistrationModel>,
                                response: Response<EventCustomerRegistrationModel>
                            ) {
                                if (response.code() == 200){
                                    Toast.makeText(activity, "Thank You for Registration !! Event Details will be sent shortly", Toast.LENGTH_SHORT).show()
                                    dismiss()
                                }
                            }

                            override fun onFailure(
                                call: Call<EventCustomerRegistrationModel>,
                                t: Throwable
                            ) {
                                Toast.makeText(activity, "Registration Failed", Toast.LENGTH_SHORT).show()
                                dismiss()
                            }
                        })

                    }

                }

            }.show()
        }
    }
}