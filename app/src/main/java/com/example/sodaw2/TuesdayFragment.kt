package com.example.sodaw2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TuesdayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tuesday, container, false)

        // Initialize all RecyclerViews and set up their LayoutManagers
        val grid1: RecyclerView = view.findViewById(R.id.grid1)
        grid1.layoutManager = GridLayoutManager(context, 2) // 2 columns

        val grid2: RecyclerView = view.findViewById(R.id.grid2)
        grid2.layoutManager = GridLayoutManager(context, 2)

        val grid3: RecyclerView = view.findViewById(R.id.grid3)
        grid3.layoutManager = GridLayoutManager(context, 2)

        val grid4: RecyclerView = view.findViewById(R.id.grid4)
        grid4.layoutManager = GridLayoutManager(context, 2)

        val grid5: RecyclerView = view.findViewById(R.id.grid5)
        grid5.layoutManager = GridLayoutManager(context, 2)

        val grid6: RecyclerView = view.findViewById(R.id.grid6)
        grid6.layoutManager = GridLayoutManager(context, 2)

        val grid7: RecyclerView = view.findViewById(R.id.grid7)
        grid7.layoutManager = GridLayoutManager(context, 2)

        val grid8: RecyclerView = view.findViewById(R.id.grid8)
        grid8.layoutManager = GridLayoutManager(context, 2)

        val grid9: RecyclerView = view.findViewById(R.id.grid9)
        grid9.layoutManager = GridLayoutManager(context, 2)

        val grid10: RecyclerView = view.findViewById(R.id.grid10)
        grid10.layoutManager = GridLayoutManager(context, 2)

        // Define the data sets for each grid with ranks
        val dataSet1 = listOf(
            GridItem(R.drawable.image24, "무셔핑","노멀", "N"),
            GridItem(R.drawable.image10, "시러핑","노멀", "N")
        )
        val dataSet2 = listOf(
            GridItem(R.drawable.image13, "덜덜핑","노멀", "N"),
            GridItem(R.drawable.image14, "그림핑","노멀", "N")
        )
        val dataSet3 = listOf(
            GridItem(R.drawable.image15, "무거핑","노멀", "N"),
            GridItem(R.drawable.image2, "똑똑핑","노멀", "N")
        )
        val dataSet4 = listOf(
            GridItem(R.drawable.image5, "찌릿핑","노멀", "N"),
            GridItem(R.drawable.image4, "꽁꽁핑","노멀", "N")
        )
        val dataSet5 = listOf(
            GridItem(R.drawable.image7, "떠벌핑","노멀", "N"),
            GridItem(R.drawable.image8, "다조핑","노멀", "N")
        )
        val dataSet6 = listOf(
            GridItem(R.drawable.image18, "베베핑","에픽", "E"),
            GridItem(R.drawable.image6, "차캐핑","에픽", "E")
        )
        val dataSet7 = listOf(
            GridItem(R.drawable.image19, "코자핑","에픽", "E"),
            GridItem(R.drawable.image20, "모야핑","에픽", "E")
        )
        val dataSet8 = listOf(
            GridItem(R.drawable.image21, "아휴핑","에픽", "E"),
            GridItem(R.drawable.image22, "앙대핑", "레어", "R")
        )
        val dataSet9 = listOf(
            GridItem(R.drawable.image11, "바네핑","레어", "R"),
            GridItem(R.drawable.image23, "공쥬핑","레어", "R")
        )
        val dataSet10 = listOf(
            GridItem(R.drawable.image3, "하츄핑","슈퍼레어", "SR"),
            GridItem(R.drawable.image12, "악동핑","히든", "H")
        )

        // Set the adapters for each RecyclerView
        grid1.adapter = GridAdapter(dataSet1)
        grid2.adapter = GridAdapter(dataSet2)
        grid3.adapter = GridAdapter(dataSet3)
        grid4.adapter = GridAdapter(dataSet4)
        grid5.adapter = GridAdapter(dataSet5)
        grid6.adapter = GridAdapter(dataSet6)
        grid7.adapter = GridAdapter(dataSet7)
        grid8.adapter = GridAdapter(dataSet8)
        grid9.adapter = GridAdapter(dataSet9)
        grid10.adapter = GridAdapter(dataSet10)

        // Return the inflated view
        return view
    }
}
