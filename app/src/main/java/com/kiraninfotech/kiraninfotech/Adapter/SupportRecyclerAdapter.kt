package com.kiraninfotech.kiraninfotech.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.internal.c
import com.kiraninfotech.kiraninfotech.Model.ContactModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.support_row.view.*


class SupportRecyclerAdapter (val context: Context, private val arrContacts: ArrayList<ContactModel>): RecyclerView.Adapter<SupportRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val officeName = view.officeName!!
        val officeAddress = view.officeAddress!!
        val officePhone = view.officePhone!!
        val officeMobile = view.officeMobile!!
        val officeEmail = view.officeEmail!!
        val officeEmailSend = view.officeEmailSend!!
        val officeWhatsAppNo = view.officeWhatsAppNo!!
        val officeWhatsAppSend = view.officeWhatsAppSend!!
        val officeSupport = view.officeSupport!!
        val officeSupportCall = view.officeSupportCall!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.support_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.officeName.text = arrContacts[position].name
        holder.officeAddress.text = arrContacts[position].address
        holder.officePhone.text = arrContacts[position].phone
        holder.officeMobile.text = arrContacts[position].mobile
        holder.officeEmail.text = arrContacts[position].email
        holder.officeSupport.text = arrContacts[position].supportno
        holder.officeWhatsAppNo.text = arrContacts[position].whatsappno


        holder.officeEmailSend.setOnClickListener {

            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "message/rfc822"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(arrContacts[position].email))
            emailIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(emailIntent)
        }

        holder.officeSupportCall.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:${arrContacts[position].supportno}")
            callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(callIntent)
        }

        holder.officeWhatsAppSend.setOnClickListener {

            val url = "https://api.whatsapp.com/send?phone=+91${arrContacts[position].whatsappno}"
            val openWhatsappIntent = Intent(Intent.ACTION_VIEW)
            openWhatsappIntent.data = Uri.parse(url)
            openWhatsappIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(openWhatsappIntent)
        }

    }

    override fun getItemCount(): Int {
        return arrContacts.size
    }
}