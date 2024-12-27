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
        val description: String
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

        // 이름-이미지 맵 생성
        val imageMap = mapOf(
            "무셔핑" to R.drawable.image24,
            "시러핑" to R.drawable.image10,
            "바네핑" to R.drawable.image11,
            "악동핑" to R.drawable.image12,
            "덜덜핑" to R.drawable.image13,
            "그림핑" to R.drawable.image14,
            "무거핑" to R.drawable.image15,
            "베베핑" to R.drawable.image18,
            "코자핑" to R.drawable.image19,
            "모야핑" to R.drawable.image20,
            "하츄핑" to R.drawable.image3,
            "차캐핑" to R.drawable.image6,
            "똑똑핑" to R.drawable.image2,
            "공주핑" to R.drawable.image23,
            "떠벌핑" to R.drawable.image7,
            "다조핑" to R.drawable.image8,
            "앙대핑" to R.drawable.image22,
            "아휴핑" to R.drawable.image21,
            "찌릿핑" to R.drawable.image5,
            "꽁꽁핑" to R.drawable.image4
        )

        // 클릭 이벤트 처리
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedContact = contactList[position]
            val selectedImageRes = imageMap[selectedContact.name] ?: R.drawable.default_image

            // 팝업 창 표시
            val dialog = ContactInfoDialogFragment.newInstance(selectedContact.name, selectedImageRes)
            dialog.show(parentFragmentManager, "ContactInfoDialog")
        }

        return rootView
    }
    // 파일 읽기 유틸리티 함수
    fun Context.readJsonFileFromAssets(fileName: String): String {
        return assets.open(fileName).bufferedReader().use { it.readText() }
    }
}
