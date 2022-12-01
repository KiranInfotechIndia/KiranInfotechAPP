package com.kiraninfotech.kiraninfotech.Adapter

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiraninfotech.kiraninfotech.Interface.EventRegistrationDialog
import com.kiraninfotech.kiraninfotech.Model.OnlineEventModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.online_event_row.view.*
import java.text.SimpleDateFormat

class OnlineEventRecyclerAdapter(val context: Context, val activity: Activity, private val arrOnlineEvents : ArrayList<OnlineEventModel>): RecyclerView.Adapter<OnlineEventRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {
        val eventName = view.eventName!!
        val eventDate = view.eventDate!!
        val eventTime = view.eventTime!!
        val eventAgendaRecyclerView = view.eventAgendaRecyclerView!!
        val eventRegisterButton = view.btnEventRegister

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.online_event_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.eventName.text = arrOnlineEvents[position].name
        holder.eventDate.text = arrOnlineEvents[position].date


        holder.eventTime.text = "${arrOnlineEvents[position].starttime} to ${arrOnlineEvents[position].totime}"

        val lineCount = arrOnlineEvents[position].agenda.lines().count()

        val arrEventAgenda = ArrayList<String>()

        for (i in 0 until lineCount){
            arrEventAgenda.add("${context.getString(R.string.bulletPoint)} ${arrOnlineEvents[position].agenda.lines()[i]}")
        }

        holder.eventAgendaRecyclerView.layoutManager = LinearLayoutManager(context)
        holder.eventAgendaRecyclerView.adapter = OnlineEventAgendaRecyclerAdapter(context, arrEventAgenda)

        val spannable = SpannableString(context.getString(R.string.register_now))

        spannable.setSpan(RelativeSizeSpan(0.5f), 0,context.getString(R.string.register_now).length - 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.eventRegisterButton.text = spannable

        holder.eventRegisterButton.setOnClickListener {
            EventRegistrationDialog.openEventRegistrationDialog(activity, arrOnlineEvents[position].id)
        }

    }

    override fun getItemCount(): Int {
        return arrOnlineEvents.size
    }
}