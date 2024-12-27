package com.example.sodaw2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MondayFragment : Fragment() {
    data class Contact(
        val name: String,
        val phone: String
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_monday, container, false)

        val jsonString = requireContext().readJsonFileFromAssets("contacts.json")
        val gson = Gson()
        val contactList: List<Contact> = gson.fromJson(jsonString, object : TypeToken<List<Contact>>() {}.type)

        val listView: ListView = rootView.findViewById(R.id.listView)
        // val formattedContacts = contactList.map { "${it.name}: ${it.phone}" }
        val adapter = ContactAdapter(requireContext(), contactList)
        listView.adapter = adapter

        return rootView
    }
    // 파일 읽기 유틸리티 함수
    fun Context.readJsonFileFromAssets(fileName: String): String {
        return assets.open(fileName).bufferedReader().use { it.readText() }
    }
}
