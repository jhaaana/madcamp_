package com.example.sodaw2

import android.content.Context
import android.content.SharedPreferences

// GridItem.kt
data class GridItem(
    val imageResId: Int,
    val name: String,
    val description: String,
    val rank: String,
    var isHidden: Boolean = true,
    var isShown: Boolean = false
) {
    val rankColor: Int
        get() = when (rank) {
            "E" -> R.drawable.green_gradinet // 초록색
            "R" -> R.drawable.gradient_purple // 보라색
            "H" -> R.drawable.hiddenfinal_1// 검정색
            "SR" -> R.drawable.gold_gradient// 금색
            "UN" -> R.drawable.unknown_gradient
            else -> R.drawable.gradation_blue // N 랭크, 오렌지색
        }
    // SharedPreferences를 통해 상태 저장
    fun saveState(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("TiniPingData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(name, isHidden) // name을 키로 사용하여 isHidden 상태 저장
        editor.apply()
    }

    // SharedPreferences를 통해 상태 불러오기
    fun loadState(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("TiniPingData", Context.MODE_PRIVATE)
        isHidden = sharedPreferences.getBoolean(name, true) // 기본값은 true (숨김 상태)
    }
}
