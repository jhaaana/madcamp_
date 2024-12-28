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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tuesday, container, false)

        // Find views
        val toggleSwitch: Switch = view.findViewById(R.id.toggleSwitch)
        val grid1: RecyclerView = view.findViewById(R.id.grid1)

        // Define the data sets
        val dataSet1 = listOf(
            GridItem(R.drawable.image24, "무셔핑", "노멀", "N"),
            GridItem(R.drawable.image10, "시러핑", "노멀", "N"),
            GridItem(R.drawable.image13, "덜덜핑","노멀", "N"),
            GridItem(R.drawable.image14, "그림핑","노멀", "N"),
            GridItem(R.drawable.image15, "무거핑","노멀", "N"),
            GridItem(R.drawable.image2, "똑똑핑","노멀", "N"),
            GridItem(R.drawable.image5, "찌릿핑","노멀", "N"),
            GridItem(R.drawable.image4, "꽁꽁핑","노멀", "N"),
            GridItem(R.drawable.image7, "떠벌핑","노멀", "N"),
            GridItem(R.drawable.image8, "다조핑","노멀", "N"),
            GridItem(R.drawable.image18, "베베핑","에픽", "E"),
            GridItem(R.drawable.image6, "차캐핑","에픽", "E"),
            GridItem(R.drawable.image19, "코자핑","에픽", "E"),
            GridItem(R.drawable.image20, "모야핑","에픽", "E"),
            GridItem(R.drawable.image21, "아휴핑","에픽", "E"),
            GridItem(R.drawable.image22, "앙대핑", "레어", "R"),
            GridItem(R.drawable.image11, "바네핑","레어", "R"),
            GridItem(R.drawable.image23, "공쥬핑","레어", "R"),
            GridItem(R.drawable.image3, "하츄핑","슈퍼레어", "SR"),
            // GridItem(R.drawable.image12, "악동핑","히든", "H")
        )
        val hiddenDataSet1 = dataSet1.map {
            GridItem(R.drawable.question_mark, "???", "???", "UN") // Placeholder
        }

        // Set up the adapter
        val adapter = GridAdapter(dataSet1.toMutableList()) // Mutable list for dynamic changes
        grid1.layoutManager = GridLayoutManager(context, 2)
        grid1.adapter = adapter

        // Add listener to toggle between datasets
        toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
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

        }

        return view
    }
    private fun showPasswordDialog(callback: (Boolean) -> Unit) {
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
    }
}
