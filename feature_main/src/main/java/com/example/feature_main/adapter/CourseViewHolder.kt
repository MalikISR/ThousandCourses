package com.example.feature_main.adapter

import android.graphics.Color
import android.graphics.Matrix
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.Course
import com.example.feature_main.databinding.ItemCourseBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class CourseViewHolder(
    private val binding: ItemCourseBinding,
    private val onLikeClick: (Course) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(course: Course) {
        binding.title.text = course.title
        binding.description.text = course.text
        binding.price.text = course.price
        binding.rate.text = course.rate

        val formattedDate = try {
            val parsedDate = LocalDate.parse(course.startDate)
            parsedDate.format(
                DateTimeFormatter.ofPattern(
                    "dd MMMM yyyy", Locale("ru")
                )
            )
        } catch (e: Exception) {
            course.startDate
        }
        binding.courseDate.text = formattedDate

        val imageView = binding.courseImage
        imageView.scaleType = ImageView.ScaleType.MATRIX

        imageView.post {
            val drawable = imageView.drawable
            if (drawable != null) {
                val matrix = Matrix()
                val viewWidth = imageView.width.toFloat()
                val drawableWidth = drawable.intrinsicWidth.toFloat()
                val scale = viewWidth / drawableWidth

                matrix.setScale(scale, scale)
                matrix.postTranslate(0f, 0f)
                imageView.imageMatrix = matrix
                imageView.scaleType = ImageView.ScaleType.MATRIX
            }
        }

        updateLikeIcon(binding.likeIcon, course.hasLike)

        binding.likeContainer.setOnClickListener {
            onLikeClick(course)
        }
    }


    private fun updateLikeIcon(imageView: ImageView, isLiked: Boolean) {
        val drawable = imageView.drawable?.mutate()
        if (drawable != null) {
            val wrapped = DrawableCompat.wrap(drawable)
            val color = if (isLiked) Color.GREEN else Color.WHITE
            DrawableCompat.setTint(wrapped, color)
            imageView.setImageDrawable(wrapped)
        }
    }
}
