package com.kiraninfotech.kiraninfotech.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kiraninfotech.kiraninfotech.Model.AcademyCourseModel
import com.kiraninfotech.kiraninfotech.R
import kotlinx.android.synthetic.main.academy_course_row.view.*

class CourseAcademyRecyclerAdapter(val context: Context, val arrCourse : ArrayList<AcademyCourseModel>): RecyclerView.Adapter<CourseAcademyRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val view : View): RecyclerView.ViewHolder(view){
        val courseName = view.courseName
        val coursePrice = view.coursePrice
        val courseDescription = view.courseDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.academy_course_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.courseName.text = arrCourse[position].name
        holder.coursePrice.text = "\u20B9 ${arrCourse[position].price}"
        holder.courseDescription.text = arrCourse[position].description
    }

    override fun getItemCount(): Int {
        return arrCourse.size
    }


}