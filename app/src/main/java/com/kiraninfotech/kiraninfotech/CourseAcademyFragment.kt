package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiraninfotech.kiraninfotech.Adapter.CourseAcademyRecyclerAdapter
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Interface.ProgressBarDialog
import com.kiraninfotech.kiraninfotech.Model.AcademyCourseModel
import kotlinx.android.synthetic.main.fragment_course_academy.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CourseAcademyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_course_academy, container, false)

        view.academyCourseRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val progressBar = ProgressBarDialog.openProgressBarDialog(requireActivity())
        progressBar.setCancelable(false)
        progressBar.show()

        ApiInterface.create().getAcademyCourse().enqueue(object : Callback<List<AcademyCourseModel>>{
            override fun onResponse(
                call: Call<List<AcademyCourseModel>>,
                response: Response<List<AcademyCourseModel>>
            ) {
                if (response.code() == 200){
                    view.academyCourseRecyclerView.adapter = CourseAcademyRecyclerAdapter(requireContext(), response.body() as ArrayList<AcademyCourseModel>)
                    progressBar.dismiss()
                }
            }

            override fun onFailure(call: Call<List<AcademyCourseModel>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }


        })


        return view
    }

}