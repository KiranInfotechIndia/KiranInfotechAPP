package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kiraninfotech.kiraninfotech.ViewPager.AcademyViewPageAdaptor
import com.kiraninfotech.kiraninfotech.ViewPager.EventViewPageAdaptor
import kotlinx.android.synthetic.main.fragment_academy.view.*
import kotlinx.android.synthetic.main.fragment_event.view.*


class AcademyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_academy, container, false)

        val academyVPAdaptor = AcademyViewPageAdaptor(requireFragmentManager())

        view.academyViewPager.adapter = academyVPAdaptor

        view.academyTab.setupWithViewPager(view.academyViewPager)

        return view
    }

}