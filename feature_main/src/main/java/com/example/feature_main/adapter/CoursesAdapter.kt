package com.example.feature_main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.core.model.Course
import com.example.feature_main.databinding.ItemCourseBinding

class CoursesAdapter(
    private val onLikeClick: (Course) -> Unit
) : ListAdapter<Course, CourseViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Course, newItem: Course) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding, onLikeClick)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
