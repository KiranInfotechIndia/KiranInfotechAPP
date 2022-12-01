package com.kiraninfotech.kiraninfotech.Interface

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.widget.Toast
import com.kiraninfotech.kiraninfotech.Model.EnquiryModel
import com.kiraninfotech.kiraninfotech.Model.StudentRegistrationModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.enquiry_form_dialog.*
import kotlinx.android.synthetic.main.student_registration_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface StudentRegistrationDialog {


    companion object{

        fun openStudentRegistrationDialog(activity: Activity, batchID : String){

            Dialog(activity).apply {
                setContentView(R.layout.student_registration_dialog)

                this.btnSubmitRegistration.setOnClickListener {
                    if(this.studentName.text.toString() == "" || this.studentMobile.text.toString() == ""){
                        Toast.makeText(activity, "Please fill Required Details", Toast.LENGTH_SHORT).show()
                    }else{

                        val studentData = StudentRegistrationModel(
                            this.studentName.text.toString(),
                            this.studentMobile.text.toString(),
                            this.studentCompany.text.toString(),
                            batchID)


                        ApiInterface.create().addStudentRegistration(studentData).enqueue(object : Callback<StudentRegistrationModel>{
                            override fun onResponse(
                                call: Call<StudentRegistrationModel>,
                                response: Response<StudentRegistrationModel>
                            ) {
                                if(response.code() == 200){
                                    Toast.makeText(activity, " \uD83D\uDE4F Thank You for showing interest on us we will get back to you soon", Toast.LENGTH_SHORT).show()
                                    dismiss()
                                }

                            }

                            override fun onFailure(
                                call: Call<StudentRegistrationModel>,
                                t: Throwable
                            ) {
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