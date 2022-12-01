package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiraninfotech.kiraninfotech.Adapter.SupportRecyclerAdapter
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Interface.ProgressBarDialog
import com.kiraninfotech.kiraninfotech.Model.ContactModel
import kotlinx.android.synthetic.main.fragment_support.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SupportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_support, container, false)


        view.supportRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val progressBar = ProgressBarDialog.openProgressBarDialog(requireActivity())
        progressBar.setCancelable(false)
        progressBar.show()

        ApiInterface.create().getContacts().enqueue(object : Callback<List<ContactModel>>{
            override fun onResponse(
                call: Call<List<ContactModel>>,
                response: Response<List<ContactModel>>
            ) {
                if (response.code() == 200){
                    view.supportRecyclerView.adapter = SupportRecyclerAdapter(requireContext(), response.body() as ArrayList<ContactModel>)
                    progressBar.dismiss()
                }
            }

            override fun onFailure(call: Call<List<ContactModel>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })

        return view
    }

}