package com.example.sodaw2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridAdapter(private val dataSet: List<GridItem>) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: View = view.findViewById(R.id.card_view)
        val imageView: ImageView = view.findViewById(R.id.itemImage)
        val nameTextView: TextView = view.findViewById(R.id.itemName)
        val itemDescription: TextView = view.findViewById(R.id.itemDescription)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_item, parent, false) // Use your grid_item layout
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gridItem = dataSet[position]

        // Set the image
        holder.imageView.setImageResource(gridItem.imageResId)

        // Set the text
        holder.nameTextView.text = gridItem.name

        //set description
        holder.itemDescription.text = gridItem.description

        // Set the background color based on the rank
        holder.cardView.setBackgroundResource(gridItem.rankColor)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = dataSet.size
}
