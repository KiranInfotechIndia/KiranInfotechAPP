package com.kiraninfotech.kiraninfotech.Adapter

import android.app.Activity
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiraninfotech.kiraninfotech.Interface.EventRegistrationDialog
import com.kiraninfotech.kiraninfotech.Model.OfflineEventModel
import com.kiraninfotech.kiraninfotech.Model.OnlineEventModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.offline_event_row.view.*
import kotlinx.android.synthetic.main.online_event_row.view.*
import kotlinx.android.synthetic.main.online_event_row.view.btnEventRegister
import kotlinx.android.synthetic.main.online_event_row.view.eventAgendaRecyclerView
import kotlinx.android.synthetic.main.online_event_row.view.eventDate
import kotlinx.android.synthetic.main.online_event_row.view.eventName
import kotlinx.android.synthetic.main.online_event_row.view.eventTime

class OfflineEventRecyclerAdapter(val context: Context, val activity: Activity, private val arrOfflineEvents : ArrayList<OfflineEventModel>): RecyclerView.Adapter<OfflineEventRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {
        val eventName = view.eventName!!
        val eventDate = view.eventDate!!
        val eventTime = view.eventTime!!
        val eventVenue = view.eventVenue!!
        val eventAgendaRecyclerView = view.eventAgendaRecyclerView!!
        val eventRegisterButton = view.btnEventRegister

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.offline_event_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.eventName.text = arrOfflineEvents[position].name
        holder.eventDate.text = arrOfflineEvents[position].date


        holder.eventTime.text = "${arrOfflineEvents[position].starttime} to ${arrOfflineEvents[position].totime}"

        val lineCount = arrOfflineEvents[position].agenda.lines().count()

        val arrEventAgenda = ArrayList<String>()

        for (i in 0 until lineCount){
            arrEventAgenda.add("${context.getString(R.string.bulletPoint)} ${arrOfflineEvents[position].agenda.lines()[i]}")
        }


        holder.eventAgendaRecyclerView.layoutManager = LinearLayoutManager(context)
        holder.eventAgendaRecyclerView.adapter = OfflineEventAgendaRecyclerAdapter(context, arrEventAgenda)

        holder.eventVenue.text = arrOfflineEvents[position].venue

        val spannable = SpannableString(context.getString(R.string.register_now))
        spannable.setSpan(RelativeSizeSpan(0.5f), 0,context.getString(R.string.register_now).length - 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.eventRegisterButton.text = spannable

        holder.eventRegisterButton.setOnClickListener {
            EventRegistrationDialog.openEventRegistrationDialog(activity, arrOfflineEvents[position].id)
        }

    }

    override fun getItemCount(): Int {
        return arrOfflineEvents.size
    }
}