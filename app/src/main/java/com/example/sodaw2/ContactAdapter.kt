package com.example.sodaw2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.ImageView

class ContactAdapter(private val context: Context, private val contacts: List<MondayFragment.Contact>) : BaseAdapter() {
    override fun getCount(): Int = contacts.size

    override fun getItem(position: Int): MondayFragment.Contact = contacts[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)

        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val backgroundImageView: ImageView = view.findViewById(R.id.backgroundImageView)

        val contact = getItem(position)
        tvName.text = contact.name
        tvDescription.text = contact.description

        val backgroundImage = MondayFragment.imageMap[contact.name] ?: R.drawable.default_image
        backgroundImageView.setImageResource(backgroundImage)

        return view
    }
}
