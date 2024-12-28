package com.example.sodaw2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class WednesdayFragment : Fragment() {
    private var score = 0
    private val SCORE_KEY = "score_key" // SharedPreferences에 사용할 키

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wednesday, container, false)

        // SharedPreferences에서 점수 복원
        // *** val sharedPreferences = requireContext().getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
        // *** score = sharedPreferences.getInt(SCORE_KEY, 0)

        // 텍스트와 이미지뷰 초기화
        val scoreText: TextView = view.findViewById(R.id.scoreText)
        val eggImage: ImageView = view.findViewById(R.id.eggImage)

        // 초기 점수 표시
        scoreText.text = "Score: $score"

        // 알 이미지 클릭 리스너
        eggImage.setOnClickListener {
            score++ // 점수 증가
            scoreText.text = "Score: $score"

            // 점수를 SharedPreferences에 저장
            // *** sharedPreferences.edit().putInt(SCORE_KEY, score).apply()

            // 알 이미지 무작위 위치로 이동
            view.post {
                //moveEggRandomly(eggImage, view.width, view.height)
            }
        }

        return view
    }

    private fun moveEggRandomly(eggImage: ImageView, containerWidth: Int, containerHeight: Int) {
        // 이미지뷰가 컨테이너를 벗어나지 않도록 조정
        val randomX = Random.nextInt(0, containerWidth - eggImage.width)
        val randomY = Random.nextInt(0, containerHeight - eggImage.height)

        // 애니메이션 효과로 위치 이동
        eggImage.animate()
            .x(randomX.toFloat())
            .y(randomY.toFloat())
            .setDuration(300) // 이동 시간 (밀리초)
            .start()
    }
}
