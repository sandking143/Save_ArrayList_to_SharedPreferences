package com.example.savearraylisttosharedpreferences


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CourseAdapter(
    private val courseModalArrayList: ArrayList<CourseModal>
) :


    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.course_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modal = courseModalArrayList[position]
        holder.courseNameTV.text = modal.courseName
        holder.courseDescTV.text = modal.courseDescription
    }

    override fun getItemCount(): Int {
        return courseModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourseName)
        val courseDescTV: TextView = itemView.findViewById(R.id.idTVCourseDescription)
    }
}
