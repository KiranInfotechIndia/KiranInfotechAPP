package com.kiraninfotech.kiraninfotech

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        view.btnProducts.setOnClickListener {
            startActivity(Intent(requireActivity().applicationContext, ProductsActivity::class.java))
        }

        view.btnSolutions.setOnClickListener {
            startActivity(Intent(requireActivity().applicationContext, SolutionsActivity::class.java))
        }

        view.btnTallyOnAws.setOnClickListener {
            startActivity(Intent(requireActivity().applicationContext, AwsPlanActivity::class.java))
        }

        view.btnTraining.setOnClickListener {
            startActivity(Intent(requireActivity().applicationContext, AcademyActivity::class.java))
        }

        return  view
    }
}