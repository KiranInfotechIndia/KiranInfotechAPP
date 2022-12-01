package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiraninfotech.kiraninfotech.Adapter.ClassAcademyRecyclerAdapter
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Interface.ProgressBarDialog
import com.kiraninfotech.kiraninfotech.Model.AcademyClassModel
import kotlinx.android.synthetic.main.fragment_class_academy.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassAcademyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_class_academy, container, false)

        view.academyClassRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val progressBar = ProgressBarDialog.openProgressBarDialog(requireActivity())
        progressBar.setCancelable(false)
        progressBar.show()

        ApiInterface.create().getAcademyClasses().enqueue(object :Callback<List<AcademyClassModel>>{
            override fun onResponse(
                call: Call<List<AcademyClassModel>>,
                response: Response<List<AcademyClassModel>>
            ) {
                if (response.code() == 200){
                    view.academyClassRecyclerView.adapter = ClassAcademyRecyclerAdapter(requireContext(), requireActivity(), response.body() as ArrayList<AcademyClassModel>)
                    progressBar.dismiss()
                }
            }

            override fun onFailure(call: Call<List<AcademyClassModel>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })

        return view
    }

}