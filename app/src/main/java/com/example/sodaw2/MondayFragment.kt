package com.example.sodaw2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class MondayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_monday, container, false)

        // ListView와 데이터 연동
        val listView: ListView = rootView.findViewById(R.id.listView)
        val contactList = listOf("John Doe", "Jane Smith", "Alice Johnson", "Bob Brown")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            contactList
        )
        listView.adapter = adapter

        return rootView
    }
}
