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
    private var rareProbability = 0
    private var superRareProbability = 0
    private var hiddenProbability = 0

    // 등급별 티니핑 이름
    private val normalTiniPings = mapOf(
        "무셔핑" to R.drawable.image24,
        "시러핑" to R.drawable.image10,
        "덜덜핑" to R.drawable.image13,
        "그림핑" to R.drawable.image14,
        "무거핑" to R.drawable.image15,
        "똑똑핑" to R.drawable.image2,
        "찌릿핑" to R.drawable.image5,
        "꽁꽁핑" to R.drawable.image4,
        "떠벌핑" to R.drawable.image7,
        "다조핑" to R.drawable.image8
    )
    private val epicTiniPings = mapOf(
        "베베핑" to R.drawable.image18,
        "차캐핑" to R.drawable.image6,
        "코자핑" to R.drawable.image19,
        "모야핑" to R.drawable.image20,
        "아휴핑" to R.drawable.image21
    )
    private val rareTiniPings = mapOf(
        "앙대핑" to R.drawable.image22,
        "바네핑" to R.drawable.image11,
        "공쥬핑" to R.drawable.image23
    )
    private val superRareTiniPings = mapOf(
        "하츄핑" to R.drawable.image3,
        "악동핑" to R.drawable.image12
    )
    /*private val hiddenTiniPings = mapOf(

    )*/

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
            if (score > 10) normalProbability += 10
            if (score > 20) epicProbability += 10
            if (score > 30) rareProbability += 10
            if (score > 40) superRareProbability += 10

                // *** sharedPreferences.edit().putInt("normal_probability", normalProbability).apply()

            // 점수를 SharedPreferences에 저장
            // *** sharedPreferences.edit().putInt(SCORE_KEY, score).apply()

            collectItemBasedOnProbability(popupTextView)

            // 알 이미지 무작위 위치로 이동
            view.post {
                moveEggRandomly(eggImage, view.width, view.height)
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

                // 노멀 티니핑들 배열..
                val randomTiniPing = normalTiniPings.keys.random()

                SharedData.updateGridItem(name = randomTiniPing, isHidden = false)

                showPopupMessage(popupMessage, randomTiniPing, "노멀")
            }
            rand < epicProbability -> {
                // 에픽 아이템 수집
                // println("노멀 아이템 수집!")
                epicProbability = 0
                // score = 0

                // 노멀 티니핑들 배열..
                val randomTiniPing = epicTiniPings.keys.random()

                SharedData.updateGridItem(name = randomTiniPing, isHidden = false)

                showPopupMessage(popupMessage, randomTiniPing, "에픽")
            }
            rand < rareProbability -> {
                // 레어 아이템 수집
                // println("노멀 아이템 수집!")
                rareProbability = 0
                // score = 0

                // 노멀 티니핑들 배열..
                val randomTiniPing = rareTiniPings.keys.random()

                SharedData.updateGridItem(name = randomTiniPing, isHidden = false)

                showPopupMessage(popupMessage, randomTiniPing, "레어")
            }
            rand < superRareProbability -> {
                // 에픽 아이템 수집
                // println("노멀 아이템 수집!")
                superRareProbability = 0
                // score = 0

                // 노멀 티니핑들 배열..
                val randomTiniPing = superRareTiniPings.keys.random()

                SharedData.updateGridItem(name = randomTiniPing, isHidden = false)

                showPopupMessage(popupMessage, randomTiniPing, "슈퍼레어")
            }
        }
    }

    private fun showPopupMessage(popupMessage: TextView, tiniPingName: String, tiniPingRank: String) {
        // 팝업 메시지 보이기
        popupMessage.text = "$tiniPingName($tiniPingRank)을 습득했습니다!"
        popupMessage.visibility = View.VISIBLE

        val rankColor = when (tiniPingRank) {
            "노멀" -> R.drawable.gradation_blue
            "에픽" -> R.drawable.green_gradinet
            "레어" -> R.drawable.gradient_purple
            "슈퍼레어" -> R.drawable.gold_gradient
            "히든" -> R.drawable.hiddenfinal_1
            else -> R.drawable.unknown_gradient
        }
        popupMessage.setBackgroundResource(rankColor)
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