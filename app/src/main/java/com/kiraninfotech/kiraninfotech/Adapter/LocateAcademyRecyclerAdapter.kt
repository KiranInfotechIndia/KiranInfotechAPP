package com.kiraninfotech.kiraninfotech.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiraninfotech.kiraninfotech.Model.AcademyModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.academy_row.view.*

class LocateAcademyRecyclerAdapter(val context: Context, val arrAcademy: ArrayList<AcademyModel>): RecyclerView.Adapter<LocateAcademyRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val academyName = view.academyName
        val academyAddress = view.academyAddress
        val academyEmail = view.academyEmail
        val academyPhone = view.academyPhone
        val academyEmailSend = view.academyEmailSend
        val academyCall = view.academyCall


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(context).inflate(R.layout.academy_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.academyName.text = arrAcademy[position].name
        holder.academyAddress.text = arrAcademy[position].address
        holder.academyEmail.text = arrAcademy[position].email
        holder.academyPhone.text = arrAcademy[position].phone


        holder.academyEmailSend.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "message/rfc822"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(arrAcademy[position].email))
            emailIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(emailIntent)
        }

        holder.academyCall.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:${arrAcademy[position].phone}")
            callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(callIntent)
        }

    }

    override fun getItemCount(): Int {
        return arrAcademy.size
    }
}