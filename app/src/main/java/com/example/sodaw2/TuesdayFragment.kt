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
        val view = inflater.inflate(R.layout.fragment_tuesday, container, false)

        // Set up RecyclerView for grid 1
        val grid1: RecyclerView = view.findViewById(R.id.grid1)
        val layoutManager1 = GridLayoutManager(context, 2) // 2 columns
        grid1.layoutManager = layoutManager1

        val grid2: RecyclerView = view.findViewById(R.id.grid2)
        val layoutManager2 = GridLayoutManager(context, 2) // 2 columns
        grid2.layoutManager = layoutManager2

        val grid3: RecyclerView = view.findViewById(R.id.grid3)
        val layoutManager3 = GridLayoutManager(context, 2) // 2 columns
        grid3.layoutManager = layoutManager3

        val grid4: RecyclerView = view.findViewById(R.id.grid4)
        val layoutManager4 = GridLayoutManager(context, 2) // 2 columns
        grid4.layoutManager = layoutManager4

        val grid5: RecyclerView = view.findViewById(R.id.grid5)
        val layoutManager5 = GridLayoutManager(context, 2) // 2 columns
        grid5.layoutManager = layoutManager5

        val grid6: RecyclerView = view.findViewById(R.id.grid6)
        val layoutManager6 = GridLayoutManager(context, 2) // 2 columns
        grid6.layoutManager = layoutManager6

        val grid7: RecyclerView = view.findViewById(R.id.grid7)
        val layoutManager7 = GridLayoutManager(context, 2) // 2 columns
        grid7.layoutManager = layoutManager7

        val grid8: RecyclerView = view.findViewById(R.id.grid8)
        val layoutManager8 = GridLayoutManager(context, 2) // 2 columns
        grid8.layoutManager = layoutManager8

        val grid9: RecyclerView = view.findViewById(R.id.grid9)
        val layoutManager9 = GridLayoutManager(context, 2) // 2 columns
        grid9.layoutManager = layoutManager9

        val grid10: RecyclerView = view.findViewById(R.id.grid10)
        val layoutManager10 = GridLayoutManager(context, 2) // 2 columns
        grid10.layoutManager = layoutManager10

        val dataSet1 = listOf(
            GridItem(R.drawable.image24, "무셔핑"),
            GridItem(R.drawable.image10, "시러핑")
        )
        val dataSet2 = listOf(
            GridItem(R.drawable.image11, "바네핑"),
            GridItem(R.drawable.image12, "악동핑")
        )
        val dataSet3 = listOf(
            GridItem(R.drawable.image13, "덜덜핑"),
            GridItem(R.drawable.image14, "그림핑")
        )
        val dataSet4 = listOf(
            GridItem(R.drawable.image15, "무거핑"),
            GridItem(R.drawable.image18, "베베핑")
        )
        val dataSet5 = listOf(
            GridItem(R.drawable.image19, "코자핑"),
            GridItem(R.drawable.image20, "모야핑")
        )
        val dataSet6 = listOf(
            GridItem(R.drawable.image3, "하츄핑"),
            GridItem(R.drawable.image6, "차캐핑")
        )
        val dataSet7 = listOf(
            GridItem(R.drawable.image2, "똑똑핑"),
            GridItem(R.drawable.image23, "공주핑")
        )
        val dataSet8 = listOf(
            GridItem(R.drawable.image7, "떠벌핑"),
            GridItem(R.drawable.image8, "다조핑")
        )
        val dataSet9 = listOf(
            GridItem(R.drawable.image22, "앙대핑"),
            GridItem(R.drawable.image21, "아휴핑")
        )
        val dataSet10 = listOf(
            GridItem(R.drawable.image5, "찌릿핑"),
            GridItem(R.drawable.image4, "꽁꽁핑")
        )

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
        return view
    }


}

