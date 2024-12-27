package com.example.sodaw2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
data class GridItem(val imageResId: Int, val text: String)
class GridAdapter(private val dataSet: List<GridItem>) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.itemImage) // ImageView reference
        val textView: TextView = view.findViewById(R.id.itemText)   // TextView reference
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_item, parent, false) // Use the grid_item layout
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gridItem = dataSet[position]
        holder.imageView.setImageResource(gridItem.imageResId) // Set the image
        holder.textView.text = gridItem.text // Set the text
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = dataSet.size
}

//
//class GridAdapter(private val dataSet: List<Int>) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {
//
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val imageView: ImageView = view.findViewById(R.id.itemImage) // Reference to ImageView
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.grid_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // Set the image resource for the ImageView
//        holder.imageView.setImageResource(dataSet[position])
//    }
//
//    override fun getItemCount() = dataSet.size
//}
//


