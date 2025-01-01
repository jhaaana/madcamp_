package com.example.sodaw2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object SharedData {
    private val _dataSet1 = MutableLiveData(
        mutableListOf(
            GridItem(R.drawable.image24, "무셔핑", "노멀", "N", true, false),
            GridItem(R.drawable.image10, "시러핑", "노멀", "N", true, false),
            GridItem(R.drawable.image13, "덜덜핑","노멀", "N", true, false),
            GridItem(R.drawable.image14, "그림핑","노멀", "N", true, false),
            GridItem(R.drawable.image15, "무거핑","노멀", "N", true, false),
            GridItem(R.drawable.image2, "똑똑핑","노멀", "N", true, false),
            GridItem(R.drawable.image5, "찌릿핑","노멀", "N", true, false),
            GridItem(R.drawable.image4, "꽁꽁핑","노멀", "N", true, false),
            GridItem(R.drawable.image7, "떠벌핑","노멀", "N", true, false),
            GridItem(R.drawable.image8, "다조핑","노멀", "N", true, false),
            GridItem(R.drawable.image18, "베베핑","에픽", "E", true, false),
            GridItem(R.drawable.image6, "차캐핑","에픽", "E", true, false),
            GridItem(R.drawable.image19, "코자핑","에픽", "E", true, false),
            GridItem(R.drawable.image20, "모야핑","에픽", "E", true, false),
            GridItem(R.drawable.image21, "아휴핑","에픽", "E", true, false),
            GridItem(R.drawable.image22, "앙대핑", "레어", "R", true, false),
            GridItem(R.drawable.image11, "바네핑","레어", "R", true, false),
            GridItem(R.drawable.image23, "공쥬핑","레어", "R", true, false),
            GridItem(R.drawable.image3, "하츄핑","슈퍼레어", "SR", true, false),
            GridItem(R.drawable.image12, "악동핑","슈퍼레어", "SR", true, false),
            )
    )
    val dataSet1: LiveData<MutableList<GridItem>> get() = _dataSet1

    fun updateDataSet1(newData: List<GridItem>) {
        _dataSet1.postValue(newData.toMutableList()) // 데이터를 MutableList로 변환하여 반영
    }




    fun updateGridItem(name: String, isHidden: Boolean) {
        val currentData = _dataSet1.value ?: return
        val item = currentData.find { it.name == name }
        item?.isHidden = isHidden
        _dataSet1.postValue(currentData)
    }

    fun toggleAllGridItemsVisibility() {
        val currentData = _dataSet1.value ?: return
        currentData.forEach { it.isShown = !it.isShown } // true를 false로, false를 true로 전환
        _dataSet1.postValue(currentData)
    }

}
