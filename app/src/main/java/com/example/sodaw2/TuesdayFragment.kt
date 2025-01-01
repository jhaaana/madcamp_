package com.example.sodaw2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import java.io.File
class TuesdayFragment : Fragment() {
    private val gson = Gson()
    private val fileName = "dataSet1.json"
    private lateinit var adapter: GridAdapter

    // 데이터를 저장하는 함수
    private fun saveDataToFile(data: List<GridItem>) {
        val json = gson.toJson(data) // 데이터를 JSON 형식으로 변환
        val file = File(requireContext().filesDir, fileName) // 파일 경로 생성
        file.writeText(json) // 파일에 JSON 데이터 저장
    }

    // 파일에서 데이터를 불러오는 함수
    private fun loadDataFromFile(): List<GridItem> {
        val file = File(requireContext().filesDir, fileName)
        if (!file.exists()) return mutableListOf() // 파일이 없으면 빈 리스트 반환
        val json = file.readText() // 파일에서 JSON 데이터를 읽기
        return gson.fromJson(json, Array<GridItem>::class.java).toList() // JSON을 객체로 변환
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_tuesday, container, false)
        // ViewModel 초기화
        val loadedData = loadDataFromFile().toMutableList()
        if (loadedData.isNotEmpty()) {
            SharedData.updateDataSet1(loadedData)
        }


        // Find views
        val toggleSwitch: Switch = view.findViewById(R.id.toggleSwitch)
        val grid1: RecyclerView = view.findViewById(R.id.grid1)

        // 초기 상태를 꺼짐으로 설정
        toggleSwitch.isChecked = false

        adapter = GridAdapter(SharedData.dataSet1.value ?: mutableListOf())
        grid1.layoutManager = GridLayoutManager(context, 2)
        grid1.adapter = adapter

        // Observe data changes
        SharedData.dataSet1.observe(viewLifecycleOwner) { updatedList ->
            adapter.updateData(updatedList)
            saveDataToFile(updatedList)
        }

        // Add listener to toggle between datasets
        toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            // 모든 GridItem의 isShown 속성을 반대로 전환
            SharedData.toggleAllGridItemsVisibility()
        }

        return view
    }
    /*private fun showPasswordDialog(callback: (Boolean) -> Unit) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter Password")

        // Create TextInputLayout and EditText
        val inputLayout = TextInputLayout(requireContext())
        val editText = TextInputEditText(requireContext())
        editText.hint = "Password"
        inputLayout.addView(editText)

        builder.setView(inputLayout)

        builder.setPositiveButton("Submit") { dialog, _ ->
            val enteredPassword = editText.text.toString()
            dialog.dismiss()
            callback(enteredPassword == "madcamp")
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            callback(false)
        }

        builder.show()
    }*/
}
