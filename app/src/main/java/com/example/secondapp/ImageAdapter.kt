package com.example.secondapp

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat

class ImageAdapter(
    private val context: Context,
    private val imageList: List<Int>
) : BaseAdapter() {

    override fun getCount(): Int = imageList.size

    override fun getItem(position: Int): Any = imageList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView = if (convertView == null) {
            // Create a new ImageView
            ImageView(context).apply {
                layoutParams = ViewGroup.LayoutParams(250, 250)
                scaleType = ImageView.ScaleType.CENTER_CROP
                setPadding(8, 8, 8, 8)
            }
        } else {
            // Reuse the recycled view
            convertView as ImageView
        }

        try {
            // Set the image resource with null check
            if (position < imageList.size) {
                val resourceId = imageList[position]
                Log.d("ImageAdapter", "Loading image at position $position, resource ID: $resourceId")
                imageView.setImageResource(resourceId)
            }
        } catch (e: Exception) {
            // Handle any exceptions that might occur
            Log.e("ImageAdapter", "Error setting image at position $position", e)
        }

        return imageView
    }
}