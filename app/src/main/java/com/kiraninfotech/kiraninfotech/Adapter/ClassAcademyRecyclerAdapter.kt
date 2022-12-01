package com.kiraninfotech.kiraninfotech.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiraninfotech.kiraninfotech.Interface.StudentRegistrationDialog
import com.kiraninfotech.kiraninfotech.MainActivity
import com.kiraninfotech.kiraninfotech.Model.AcademyClassModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.academy_class_row.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ClassAcademyRecyclerAdapter (val context: Context, val activity: Activity, val arrClasses : ArrayList<AcademyClassModel>) : RecyclerView.Adapter<ClassAcademyRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view : View): RecyclerView.ViewHolder(view){
        val courseName = view.courseName
        val academyName = view.academyName
        val classDate = view.classDate
        val classTime = view.classTime
        val classAvailableSeats = view.classAvailableSeats
        val btnStudentRegister = view.btnStudentRegister

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.academy_class_row, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.courseName.text = arrClasses[position].coursename
        holder.academyName.text = arrClasses[position].academyname

        holder.classAvailableSeats.text = arrClasses[position].availableseats


        holder.classDate.text = (arrClasses[position].startdate)
        holder.classTime.text = "${arrClasses[position].starttime} to ${arrClasses[position].totime}"


        val spannable = SpannableString(context.getString(R.string.register_now))
        spannable.setSpan(RelativeSizeSpan(0.5f), 0,context.getString(R.string.register_now).length - 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.btnStudentRegister.text = spannable


        holder.btnStudentRegister.setOnClickListener {
            StudentRegistrationDialog.openStudentRegistrationDialog(activity, arrClasses[position].id)
        }

    }

    override fun getItemCount(): Int {
        return arrClasses.size
    }
}