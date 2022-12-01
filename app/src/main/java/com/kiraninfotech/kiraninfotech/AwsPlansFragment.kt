package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiraninfotech.kiraninfotech.Adapter.AwsPlanRecyclerAdapter
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Interface.ProgressBarDialog
import com.kiraninfotech.kiraninfotech.Model.AwsPlanModel
import kotlinx.android.synthetic.main.fragment_aws_plans.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AwsPlansFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_aws_plans, container, false)

        view.awsPlanRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val progressBar = ProgressBarDialog.openProgressBarDialog(requireActivity())
        progressBar.setCancelable(false)
        progressBar.show()

        ApiInterface.create().getAwsPlans().enqueue(object : Callback<List<AwsPlanModel>>{
            override fun onResponse(
                call: Call<List<AwsPlanModel>>,
                response: Response<List<AwsPlanModel>>
            ) {
                if(response.code() == 200){
                    view.awsPlanRecyclerView.adapter = AwsPlanRecyclerAdapter(requireContext(), requireActivity(), response.body() as ArrayList<AwsPlanModel>)
                    progressBar.dismiss()
                }
            }

            override fun onFailure(call: Call<List<AwsPlanModel>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return view
    }
}