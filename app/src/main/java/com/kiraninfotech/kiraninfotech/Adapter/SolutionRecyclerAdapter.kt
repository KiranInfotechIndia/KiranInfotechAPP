package com.kiraninfotech.kiraninfotech.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.kiraninfotech.kiraninfotech.Interface.BottomSheetDialog
import com.kiraninfotech.kiraninfotech.Interface.EnquiryFormDialog
import com.kiraninfotech.kiraninfotech.Model.SolutionModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.solutions_row.view.*

class SolutionRecyclerAdapter (val context: Context, val activity: Activity, val arrSolutions : ArrayList<SolutionModel>): RecyclerView.Adapter<SolutionRecyclerAdapter.ViewHolder>() {
    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {
        val solutionName = view.solutionName
        val solutionImg = view.solutionImg
        val solutionView = view.solutionView
        val btnVideoPlayer = view.btnPlayVideo
        val btnEnquireNow = view.btnEquiryNow

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.solutions_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.solutionName.text = arrSolutions[position].name

        val solutionImgURL = "http://tallydata.co.in/KiraninfotechApp${arrSolutions[position].imageurl}"

        Glide.with(context).load(solutionImgURL).into(holder.solutionImg)


        val solutionDescription = ArrayList<String>()
        val lineCount = arrSolutions[position].description.lines().count()

        for (i in 0 until lineCount){
            solutionDescription.add (arrSolutions[position].description.lines()[i])
        }

        holder.solutionView.setOnClickListener {

            BottomSheetDialog.openBottomSheetDialog(activity, arrSolutions[position].name, arrSolutions[position].price, solutionDescription)
        }


        holder.btnVideoPlayer.setOnClickListener {

            if (arrSolutions[position].video_id != "0"){
                val youtubeIntent = YouTubeStandalonePlayer.createVideoIntent(activity, context.getString(R.string.youtube_api), arrSolutions[position].video_id, 0, true, false)
                youtubeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(youtubeIntent)
            }else
            {
                Toast.makeText(context, "Video is not available for now", Toast.LENGTH_SHORT).show()
            }

        }

        holder.btnEnquireNow.setOnClickListener {

            EnquiryFormDialog.openEnquiryDialog(activity, arrSolutions[position].name)

        }

        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return arrSolutions.size
    }

    private fun setAnimation(view : View, position: Int){
        val slideInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.startAnimation(slideInAnimation)
    }
}