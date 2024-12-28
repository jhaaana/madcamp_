package com.example.sodaw2

// GridItem.kt
data class GridItem(
    val imageResId: Int,
    val name: String,
    val description: String,
    val rank: String
) {
    val rankColor: Int
        get() = when (rank) {
            "E" -> R.drawable.green_gradinet // 초록색
            "R" -> R.drawable.gradient_purple // 보라색
            "H" -> R.drawable.hiddenfinal_1// 검정색
            "SR" -> R.drawable.goldgold// 금색
            else -> R.color.white // N 랭크, 오렌지색
        }
}
