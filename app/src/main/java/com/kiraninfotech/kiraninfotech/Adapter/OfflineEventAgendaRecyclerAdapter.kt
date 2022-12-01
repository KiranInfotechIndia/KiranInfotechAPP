package com.kiraninfotech.kiraninfotech.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.event_agenda_row.view.*

class OfflineEventAgendaRecyclerAdapter(val context: Context, val arrEventAgenda : ArrayList<String>): RecyclerView.Adapter<OfflineEventAgendaRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {
        val eventAgenda = view.eventAgenda
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.event_agenda_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.eventAgenda.text = arrEventAgenda[position]
    }

    override fun getItemCount(): Int {
        return arrEventAgenda.size
    }
}