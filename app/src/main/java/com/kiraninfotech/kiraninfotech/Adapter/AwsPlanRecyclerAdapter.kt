package com.kiraninfotech.kiraninfotech.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.kiraninfotech.kiraninfotech.Interface.AwsPlanBottomSheetDialog
import com.kiraninfotech.kiraninfotech.Interface.EnquiryFormDialog
import com.kiraninfotech.kiraninfotech.Model.AwsPlanModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.awsplan_row.view.*
import kotlinx.android.synthetic.main.awsplan_row.view.btnEquiryNow
import kotlinx.android.synthetic.main.solutions_row.view.*

class AwsPlanRecyclerAdapter(val context: Context, val activity: Activity, val arrAwsPlans : ArrayList<AwsPlanModel>): RecyclerView.Adapter<AwsPlanRecyclerAdapter.ViewHoder>() {

    class ViewHoder(val view: View): RecyclerView.ViewHolder(view) {

        val awsPriceView = view.awsPriceView!!
        val awsPlanName = view.awsPlanName!!
        val awsPlanUser = view.awsPlanUsers!!
        val awsPlanRAM = view.awsPlanRAM!!
        val awsPlanStorage = view.awsPlanStorage!!
        val awsPlanBackup = view.awsPlanBackup!!
        val awsPlanDuration = view.awsPlanDuration!!
        val btnEquiryNow = view.btnEquiryNow

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        return ViewHoder(LayoutInflater.from(context).inflate(R.layout.awsplan_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {
        holder.awsPlanName.text = arrAwsPlans[position].name

        if (arrAwsPlans[position].users.toInt() == 1){
            holder.awsPlanUser.text = "For ${arrAwsPlans[position].users} User"
        }else{
            holder.awsPlanUser.text = "Upto ${arrAwsPlans[position].users} Users"
        }

        holder.awsPlanRAM.text = "${arrAwsPlans[position].ram} RAM"

        holder.awsPlanStorage.text = "${arrAwsPlans[position].storage} Storage"

        holder.awsPlanBackup.text = "${arrAwsPlans[position].backup} Backup Storage"

        holder.awsPlanDuration.text = arrAwsPlans[position].duration


        holder.btnEquiryNow.setOnClickListener {
            EnquiryFormDialog.openEnquiryDialog(activity, arrAwsPlans[position].name)
        }

        holder.awsPriceView.setOnClickListener {
            AwsPlanBottomSheetDialog.openAwsPricingDialog(activity, arrAwsPlans[position].name, arrAwsPlans[position].gst.toInt(), arrAwsPlans[position].taxablevalue.toInt())
        }

        setAnimation(holder.itemView, position)

    }

    override fun getItemCount(): Int {
        return arrAwsPlans.size
    }


    private fun setAnimation(view : View, position: Int){
        val slideInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.startAnimation(slideInAnimation)
    }
}