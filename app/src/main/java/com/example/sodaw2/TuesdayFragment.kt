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

class TuesdayFragment : Fragment() {
    private lateinit var adapter: GridAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tuesday, container, false)
        // ViewModel 초기화

        // Find views
        // val toggleSwitch: Switch = view.findViewById(R.id.toggleSwitch)
        val grid1: RecyclerView = view.findViewById(R.id.grid1)

        // 초기 상태를 꺼짐으로 설정
        // toggleSwitch.isChecked = false

        /*val hiddenDataSet1 = dataSet1.map {
            GridItem(R.drawable.question_mark, "???", "???", "UN") // Placeholder
        }*/

        // Set up the adapter
        // val adapter = GridAdapter(SharedData.dataSet1) // Mutable list for dynamic changes
        // grid1.layoutManager = GridLayoutManager(context, 2)
        // grid1.adapter = adapter

        adapter = GridAdapter(SharedData.dataSet1.value ?: mutableListOf())
        grid1.layoutManager = GridLayoutManager(context, 2)
        grid1.adapter = adapter

        // Observe data changes
        SharedData.dataSet1.observe(viewLifecycleOwner) { updatedList ->
            adapter.updateData(updatedList)
        }

        // Add listener to toggle between datasets
        /*toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showPasswordDialog { isCorrect ->
                    if (isCorrect) {
                        adapter.updateData(dataSet1)
                    } else {
                        toggleSwitch.isChecked = false
                        Toast.makeText(context, "Incorrect password!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                adapter.updateData(hiddenDataSet1)
            }

        }*/

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
