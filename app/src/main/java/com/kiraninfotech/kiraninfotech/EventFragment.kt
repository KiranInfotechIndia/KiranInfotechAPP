package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kiraninfotech.kiraninfotech.ViewPager.EventViewPageAdaptor
import kotlinx.android.synthetic.main.fragment_event.view.*

class EventFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_event, container, false)

        val eventVPAdaptor = EventViewPageAdaptor(requireFragmentManager())

        view.eventViewPager.adapter = eventVPAdaptor

        view.eventTab.setupWithViewPager(view.eventViewPager)

        return view
    }

}