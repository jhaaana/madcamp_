package com.example.sodaw2

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
}
