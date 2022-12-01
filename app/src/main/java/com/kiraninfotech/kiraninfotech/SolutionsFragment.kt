package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiraninfotech.kiraninfotech.Adapter.SolutionRecyclerAdapter
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Interface.ProgressBarDialog
import com.kiraninfotech.kiraninfotech.Model.SolutionModel
import kotlinx.android.synthetic.main.fragment_solutions.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SolutionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_solutions, container, false)


        view.solutionsRecyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)

        val progressBar = ProgressBarDialog.openProgressBarDialog(requireActivity())
        progressBar.setCancelable(false)
        progressBar.show()


        ApiInterface.create().getSolutions().enqueue(object : Callback<List<SolutionModel>>{
            override fun onResponse(
                call: Call<List<SolutionModel>>,
                response: Response<List<SolutionModel>>
            ) {
                if(response.code() == 200){
                    view.solutionsRecyclerView.adapter = SolutionRecyclerAdapter(requireContext(), requireActivity() , response.body() as ArrayList<SolutionModel>)
                    progressBar.dismiss()

                }
            }

            override fun onFailure(call: Call<List<SolutionModel>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }


        })

        return view


    }
}