package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiraninfotech.kiraninfotech.Adapter.ProductRecyclerAdapter
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Interface.ProgressBarDialog
import com.kiraninfotech.kiraninfotech.Model.ProductModel
import kotlinx.android.synthetic.main.fragment_product.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_product, container, false)

        view.productsRecyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)

        val progressBar = ProgressBarDialog.openProgressBarDialog(requireActivity())
        progressBar.setCancelable(false)
        progressBar.show()


        ApiInterface.create().getProducts().enqueue(object : Callback<List<ProductModel>>{
            override fun onResponse(
                call: Call<List<ProductModel>>,
                response: Response<List<ProductModel>>
            ) {
                if (response.code() == 200){
                    view.productsRecyclerView.adapter = ProductRecyclerAdapter(requireContext().applicationContext, requireActivity(),response.body() as ArrayList<ProductModel>)
                    progressBar.dismiss()
                }
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })
        return  view
    }
}