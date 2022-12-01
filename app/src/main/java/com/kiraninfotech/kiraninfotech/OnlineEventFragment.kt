package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiraninfotech.kiraninfotech.Adapter.OnlineEventRecyclerAdapter
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Interface.ProgressBarDialog
import com.kiraninfotech.kiraninfotech.Model.OnlineEventModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.fragment_online_event.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OnlineEventFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_online_event, container, false)

        view.onlineEventRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        val progressBar = ProgressBarDialog.openProgressBarDialog(requireActivity())
        progressBar.setCancelable(false)
        progressBar.show()

        ApiInterface.create().getOnlineEvents().enqueue(object : Callback<List<OnlineEventModel>>{
            override fun onResponse(
                call: Call<List<OnlineEventModel>>,
                response: Response<List<OnlineEventModel>>
            ) {
                if (response.code() == 200){
                    view.onlineEventRecyclerView.adapter = OnlineEventRecyclerAdapter(requireContext(), requireActivity(), response.body() as ArrayList<OnlineEventModel>)
                    progressBar.dismiss()
                }
            }

            override fun onFailure(call: Call<List<OnlineEventModel>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })

        return view
    }
}