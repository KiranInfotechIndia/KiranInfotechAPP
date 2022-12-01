package com.kiraninfotech.kiraninfotech.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kiraninfotech.kiraninfotech.Interface.BottomSheetDialog
import com.kiraninfotech.kiraninfotech.Interface.EnquiryFormDialog
import com.kiraninfotech.kiraninfotech.Model.ProductModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.products_row.view.*

class ProductRecyclerAdapter(val context: Context, val activity: Activity, val arrProducts : ArrayList<ProductModel>) : RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val productName = view.productName
        val productImg = view.productImg
        val productView = view.productView
        val btnEquiryNow = view.btnEquiryNow
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.products_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = arrProducts[position].name

        val productImgURL = "http://tallydata.co.in/KiraninfotechApp${arrProducts[position].imageurl}"

        Glide.with(context).load(productImgURL).into(holder.productImg)

        holder.btnEquiryNow.setOnClickListener {
            EnquiryFormDialog.openEnquiryDialog(activity, arrProducts[position].name)
        }

        holder.productView.setOnClickListener {


            val productDescription = ArrayList<String>()
            val lineCount = arrProducts[position].description.lines().count()

            for (i in 0 until lineCount){
                productDescription.add(arrProducts[position].description.lines()[i])
            }

            BottomSheetDialog.openBottomSheetDialog(activity, arrProducts[position].name, arrProducts[position].price, productDescription)

        }

        setAnimation(holder.itemView, position)

    }

    override fun getItemCount(): Int {
        return arrProducts.size
    }

    private fun setAnimation(view : View, position: Int){
        val slideInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.startAnimation(slideInAnimation)
    }
}