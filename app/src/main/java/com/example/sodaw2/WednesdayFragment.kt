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
import android.os.Handler
import android.os.Looper


class WednesdayFragment : Fragment() {
    private var score = 0
    private val SCORE_KEY = "score_key" // SharedPreferences에 사용할 키

    // 등급별 수집 확률
    private var normalProbability = 0
    private var epicProbability = 0
    private var superRareProbability = 0
    private var hiddenProbability = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wednesday, container, false)

        // SharedPreferences에서 점수 복원
        // *** val sharedPreferences = requireContext().getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
        // *** score = sharedPreferences.getInt(SCORE_KEY, 0)
        // *** normalProbability = sharedPreferences.getInt("normal_probability", 0)

        // 텍스트와 이미지뷰 초기화
        val scoreText: TextView = view.findViewById(R.id.scoreText)
        val eggImage: ImageView = view.findViewById(R.id.eggImage)
        val popupTextView: TextView = view.findViewById(R.id.popupTextView)

        // 초기 점수 표시
        scoreText.text = "Score: $score"

        // 알 이미지 클릭 리스너
        eggImage.setOnClickListener {
            score++ // 점수 증가
            scoreText.text = "Score: $score"

            // 점수에 따라 확률 업데이트 (10회마다)
            if (score % 10 == 0) {
                normalProbability += 10
                // *** sharedPreferences.edit().putInt("normal_probability", normalProbability).apply()
            }

            // 점수를 SharedPreferences에 저장
            // *** sharedPreferences.edit().putInt(SCORE_KEY, score).apply()

            collectItemBasedOnProbability(popupTextView)

            // 알 이미지 무작위 위치로 이동
            view.post {
                //moveEggRandomly(eggImage, view.width, view.height)
            }
        }

        return view
    }

    private fun collectItemBasedOnProbability(popupMessage: TextView) {
        val rand = Random.nextInt(0, 100)
        when {
            rand < normalProbability -> {
                // 노멀 아이템 수집
                // println("노멀 아이템 수집!")
                normalProbability = 0
                // score = 0
                showPopupMessage(popupMessage)
            }
        }
    }

    private fun showPopupMessage(popupMessage: TextView) {
        // 팝업 메시지 보이기
        popupMessage.visibility = View.VISIBLE

        // 애니메이션 적용 (짧게 펴졌다 사라지는 효과)
        popupMessage.animate()
            .alpha(1f)  // 투명도 1로 설정 (보이게)
            .setDuration(500) // 0.5초 동안 나타남
            .withEndAction {
                // 일정 시간 후 (예: 2초) 사라지게 함
                Handler(Looper.getMainLooper()).postDelayed({
                    popupMessage.animate()
                        .alpha(0f) // 투명도로 사라짐
                        .setDuration(500) // 0.5초 동안 사라짐
                        .withEndAction {
                            popupMessage.visibility = View.GONE // 완전히 사라지면 숨김
                        }
                        .start()
                }, 2000) // 2초 후에 사라짐
            }
            .start()
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
