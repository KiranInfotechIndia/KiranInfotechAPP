package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiraninfotech.kiraninfotech.Adapter.OfflineEventRecyclerAdapter
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Interface.ProgressBarDialog
import com.kiraninfotech.kiraninfotech.Model.OfflineEventModel
import kotlinx.android.synthetic.main.fragment_offline_event.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OfflineEventFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_offline_event, container, false)

        view.offlineEventRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val progressBar = ProgressBarDialog.openProgressBarDialog(requireActivity())
        progressBar.setCancelable(false)
        progressBar.show()

        ApiInterface.create().getOfflineEvents().enqueue(object : Callback<List<OfflineEventModel>>{
            override fun onResponse(
                call: Call<List<OfflineEventModel>>,
                response: Response<List<OfflineEventModel>>
            ) {
                if (response.code() == 200){
                    view.offlineEventRecyclerView.adapter = OfflineEventRecyclerAdapter(requireContext(),requireActivity() ,response.body() as ArrayList<OfflineEventModel>)
                    progressBar.dismiss()
                }
            }

            override fun onFailure(call: Call<List<OfflineEventModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })

        return view
    }

}