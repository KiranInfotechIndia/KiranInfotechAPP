package com.kiraninfotech.kiraninfotech

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kiraninfotech.kiraninfotech.Adapter.LocateAcademyRecyclerAdapter
import com.kiraninfotech.kiraninfotech.Interface.ApiInterface
import com.kiraninfotech.kiraninfotech.Interface.ProgressBarDialog
import com.kiraninfotech.kiraninfotech.Model.AcademyModel
import kotlinx.android.synthetic.main.fragment_locate_academy.*
import kotlinx.android.synthetic.main.fragment_locate_academy.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocateAcademyFragment : Fragment(), OnMapReadyCallback {

    lateinit var arrAcademyLocation : ArrayList<AcademyModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_locate_academy, container, false)

        view.academyRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val progressBar = ProgressBarDialog.openProgressBarDialog(requireActivity())
        progressBar.setCancelable(false)
        progressBar.show()

        ApiInterface.create().getAcademy().enqueue(object : Callback<List<AcademyModel>>{
            override fun onResponse(
                call: Call<List<AcademyModel>>,
                response: Response<List<AcademyModel>>
            ) {
                if (response.code() == 200){

                    arrAcademyLocation = response.body() as ArrayList<AcademyModel>

                    view.academyRecyclerView.adapter = LocateAcademyRecyclerAdapter(requireContext(), arrAcademyLocation)

                    val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

                    mapFragment.getMapAsync(this@LocateAcademyFragment)

                    progressBar.dismiss()

                }
            }

            override fun onFailure(call: Call<List<AcademyModel>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })

        return view

    }

    override fun onMapReady(map: GoogleMap) {
        for (model in arrAcademyLocation){
            val latLng = LatLng(model.latitude.toDouble(), model.longitude.toDouble())

            map.addMarker(MarkerOptions().apply{
                position(latLng)
                title(model.name)
                snippet(model.address)
            })

            map.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11f))
        }
    }
}