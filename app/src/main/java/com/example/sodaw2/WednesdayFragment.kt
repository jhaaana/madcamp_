package com.example.sodaw2

import android.os.Bundle
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random
import android.os.Handler
import android.os.Looper
import android.provider.Settings.Global.putInt
import android.util.Log
import androidx.core.content.ContextCompat
import android.view.MotionEvent


class WednesdayFragment : Fragment() {
    private var score = 0
    private val SCORE_KY = "score_key" // SharedPreferences에 사용할 키
    private val NORMAL_PROBABILITY_KEY = "normalProbability"


    // 등급별 수집 확률
    private var normalProbability = 0
    private var epicProbability = 0
    private var rareProbability = 0
    private var superRareProbability = 0
    private var hiddenProbability = 0

    // 꽁꽁핑 변수
    private var frozen = 0
    // 찌릿핑 변수
    private var spark = 0
    // 무거핑 변수
    private var heavy = 0
    // 덜덜핑 변수
    private var cold = 0
    // 앙대핑 변수
    private var small = 0
    // 악동핑 변수
    private var evil = 0
    // 코자핑 변수
    private var isSleeping = false
    //차캐핑(에픽)
    private var twice = 0
    //바네핑
    private var bane = 0

    private var enlarge = 0

    private var hu = 0

    private var picture = 0

    private var randomKey: String? = "하츄핑"
    // private var heart = false

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
    private val isTiniPingCollected = mutableMapOf(
        "무셔핑" to false,
        "시러핑" to false,
        "덜덜핑" to false,
        "그림핑" to false,
        "무거핑" to false,
        "똑똑핑" to false,
        "찌릿핑" to false,
        "꽁꽁핑" to false,
        "떠벌핑" to false,
        "다조핑" to false,
        "베베핑" to false,
        "차캐핑" to false,
        "코자핑" to false,
        "모야핑" to false,
        "아휴핑" to false,
        "앙대핑" to false,
        "바네핑" to false,
        "공쥬핑" to false,
        "하츄핑" to false,
        "악동핑" to false
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wednesday, container, false)

        // SharedPreferences에서 점수 불러오기
        val sharedPreferences = requireContext().getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
        score = sharedPreferences.getInt(SCORE_KY, 0)

        // 텍스트와 이미지뷰 초기화
        val scoreText: TextView = view.findViewById(R.id.scoreText)
        val eggImage: ImageView = view.findViewById(R.id.eggImage)
        val popupTextView: TextView = view.findViewById(R.id.popupTextView)
        addTouchEffect(eggImage)

        val editor = sharedPreferences.edit()

        val density = resources.displayMetrics.density
        eggImage.layoutParams = eggImage.layoutParams.apply{
            width = (200 * density).toInt()
            height = (200 * density).toInt()
        }
//         val sharedPreferences = requireContext().getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
//         score = sharedPreferences.getInt(SCORE_KY, 0)
//         normalProbability = sharedPreferences.getInt("normal_probability", 0)

        // 초기 점수 표시
        scoreText.text = "Score: $score"


        // 원하는 색상을 덧씌우기 (예: 반투명 파란색)
        val overlayColor = resources.getColor(R.color.ice, null)

        if(cold > 0) startShaking(eggImage)
        /*if(bane > 0) score += 5
        if(twice > 0) score += 2*/
        if(!isSleeping) eggImage.setImageResource(R.drawable.romi)

        /*if(heart)*/ eggImage.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 터치 시 애니메이션 추가 가능 (축소 등)
                    val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                        v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 0.9f),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.9f)
                    )
                    scaleDown.duration = 100
                    scaleDown.start()
                    false // 이벤트 소비
                }
                MotionEvent.ACTION_UP -> {
                    // 부모 뷰 그룹 내 터치 좌표 계산
                    val parentViewGroup = v.parent as? ViewGroup
                    val location = IntArray(2)
                    parentViewGroup?.getLocationOnScreen(location)

                    // 부모 기준 상대 좌표로 변환
                    val x = event.rawX - location[0]
                    val y = event.rawY - location[1]

                    showHeart(v as ImageView, x, y)

                    // 크기 복원 애니메이션
                    val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                        v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1f),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f)
                    )
                    scaleUp.duration = 100
                    scaleUp.start()
                    false // 이벤트 소비
                }
                else -> false // 다른 이벤트는 처리하지 않음
            }
        }

        // 알 이미지 클릭 리스너
        eggImage.setOnClickListener {



            if (isSleeping) {
                // 잠들어 있는 경우 팝업 메시지 표시
                popupTextView.text = "로미가 잠들어 있습니다.. 조금만 기다려주세요!"
                popupTextView.visibility = View.VISIBLE
                zinPopupMessage(popupTextView, "코자핑", "SR")
                return@setOnClickListener // 리스너 실행 중단
            }
            else{
                eggImage.setImageResource(R.drawable.romi)
            }

            if(cold > 0) cold--
            else stopShaking()

            if(twice == 0 && bane == 0 && hu == 0) score++
            else {
                if(hu > 0) {
                    hu--
                    val randomValue = (0..1).random()
                    if(randomValue == 0) score++
                }
                if (bane > 0) {
                    bane--
                    score += 5
                }
                if (twice > 0) {
                    twice--
                    score += 2
                }
            }




            //score++ // 점수 증가
            scoreText.text = "Score: $score"



            // 점수에 따라 확률 업데이트 (10회마다)
            if (score > 10) normalProbability += 10
            if (score > 20) epicProbability += 10
            if (score > 30) rareProbability += 10
            if (score > 40) superRareProbability += 10





            // *** sharedPreferences.edit().putInt("normal_probability", normalProbability).apply()
            // SharedPreferences에서 점수와 확률을 불러오고 저장하는 코드

            // 점수를 SharedPreferences에 저장
            // *** sharedPreferences.edit().putInt(SCORE_KEY, score).apply()




            val randomValue = (0..999999).random()
            if(randomValue < evil) {
                score = 0
                scoreText.text = "Score: $score"
                popupTextView.text = "악동핑 의 가호!\n점수가 초기화됩니다.."
                popupTextView.visibility = View.VISIBLE
                zinPopupMessage(popupTextView, "악동핑", "SR")
                return@setOnClickListener
            }

            collectItemBasedOnProbability(eggImage, popupTextView)

            if(enlarge > 0 ){
                enlarge--
                val density = resources.displayMetrics.density
                eggImage.layoutParams = eggImage.layoutParams.apply{
                    width = (300 * density).toInt()
                    height = (300 * density).toInt()
                }
            }else if(small > 0) {
                small--
                val density = resources.displayMetrics.density
                eggImage.layoutParams = eggImage.layoutParams.apply {
                    width = (100 * density).toInt() // 너비 변경
                    height = (100 * density).toInt() // 높이 변경
                }
            }
            else {
                val density = resources.displayMetrics.density
                eggImage.layoutParams = eggImage.layoutParams.apply{
                    width = (200 * density).toInt()
                    height = (200 * density).toInt()
                }
            }

            /*if(frozen > 0) eggImage.setColorFilter(overlayColor, android.graphics.PorterDuff.Mode.SRC_ATOP)
            else eggImage.clearColorFilter()*/
            if(frozen > 0) {
                frozen--
                eggImage.setColorFilter(overlayColor, android.graphics.PorterDuff.Mode.SRC_ATOP)
            }
            else eggImage.clearColorFilter()
            if(picture > 0) {
                picture--

                val imageResource = when {
                    normalTiniPings.containsKey(randomKey) -> normalTiniPings[randomKey]
                    epicTiniPings.containsKey(randomKey) -> epicTiniPings[randomKey]
                    rareTiniPings.containsKey(randomKey) -> rareTiniPings[randomKey]
                    superRareTiniPings.containsKey(randomKey) -> superRareTiniPings[randomKey]
                    else -> null // 해당 key가 없을 경우 null 반환
                }

                // 이미지 리소스를 ImageView에 설정
                imageResource?.let {
                    eggImage.setImageResource(it)
                } ?: run {
                    // null인 경우 처리 (예: 기본 이미지 설정)
                    eggImage.setImageResource(R.drawable.romi)
                }
            }
            else eggImage.setImageResource(R.drawable.romi)

            // 알 이미지 무작위 위치로 이동
            view.post {
                if (frozen == 0) moveEggRandomly(eggImage, view.width, view.height)
                else frozen--
            }
            editor.putInt(SCORE_KY, score)
            editor.apply()
        }

        return view
    }

    private fun collectItemBasedOnProbability(eggImage: ImageView, popupMessage: TextView) {
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

                showPopupMessage(eggImage, popupMessage, randomTiniPing, "노멀")
            }
            rand < epicProbability -> {
                // 에픽 아이템 수집
                // println("노멀 아이템 수집!")
                epicProbability = 0
                // score = 0

                // 에픽 티니핑들 배열..
                val randomTiniPing = epicTiniPings.keys.random()

                SharedData.updateGridItem(name = randomTiniPing, isHidden = false)

                showPopupMessage(eggImage, popupMessage, randomTiniPing, "에픽")
            }
            rand < rareProbability -> {
                // 레어 아이템 수집
                // println("노멀 아이템 수집!")
                rareProbability = 0
                // score = 0

                // 레어 티니핑들 배열..
                val randomTiniPing = rareTiniPings.keys.random()

                SharedData.updateGridItem(name = randomTiniPing, isHidden = false)

                showPopupMessage(eggImage, popupMessage, randomTiniPing, "레어")
            }
            rand < superRareProbability -> {
                // 에픽 아이템 수집
                // println("노멀 아이템 수집!")
                superRareProbability = 0
                // score = 0

                // 슈퍼레어 티니핑들 배열..
                val randomTiniPing = superRareTiniPings.keys.random()

                SharedData.updateGridItem(name = randomTiniPing, isHidden = false)

                showPopupMessage(eggImage, popupMessage, randomTiniPing, "슈퍼레어")
            }
        }
    }

    private fun showPopupMessage(eggImage: ImageView, popupMessage: TextView, tiniPingName: String, tiniPingRank: String) {
        if(isTiniPingCollected[tiniPingName] == false) { // 수집 안된 티니핑
                isTiniPingCollected[tiniPingName] = true
                popupMessage.text = "$tiniPingName($tiniPingRank)을 습득했습니다!"
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                // if(tiniPingName == "하츄핑") heart = true
            }
        else{ // tiniPingName에 해당하는 티니핑이 수집되었음을 의미하니깐,
            // 이제 여기서 티니핑별 함수? 구현하면됨
            if(tiniPingName == "꽁꽁핑" && frozen == 0) { // 꽁꽁핑 버프: 로미 100회동안 안움직이게
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 움직이지 않습니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                frozen = 10
            }
            if(tiniPingName == "그림핑" && picture == 0) { // 꽁꽁핑 버프: 로미 100회동안 안움직이게
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 티니핑으로 변합니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                randomKey = getRandomKeyWithTrueValue(isTiniPingCollected)
                picture = 10
            }
            else if((tiniPingName == "찌릿핑" || tiniPingName == "떠벌핑") && spark == 0) { // 찌릿핑 버프: 로미 100회동안 이동속도 증가
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 더 빠르게 움직입니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                spark = 10
            }
            else if((tiniPingName == "무거핑" || tiniPingName == "똑똑핑") && heavy == 0) { // 무거핑 버프: 로미 100회동안 이동속도 감소
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 더 느리게 움직입니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                heavy = 10
            }
            else if((tiniPingName == "덜덜핑" || tiniPingName == "무셔핑") && cold == 0) { // 덜덜핑 버프: 로미 100회동안 덜덜더럳ㄹ
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 덜덜 떱니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                cold = 10
            }
            else if(tiniPingName == "앙대핑" && small == 0) { // 앙대핑 버프: 로미 100회동안 크기 감소
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 크기가 감소합니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                small = 10
            }
            else if(tiniPingName == "시러핑") { // 시러핑 버프: 로미가 수집한 노멀티니핑하나 없앰
                popupMessage.text = "$tiniPingName 의 가호!\n노멀 티니핑들 중 한 마리를 잃어버렸습니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                val deletedTiniPing = normalTiniPings.keys.random()
                isTiniPingCollected[deletedTiniPing] = false
                SharedData.updateGridItem(name = deletedTiniPing, isHidden = true)
            }
            else if(tiniPingName == "모야핑") { // 모야핑 버프: 로미가 미수집한 에픽티니핑하나 수집
                popupMessage.text = "$tiniPingName 의 가호!\n에픽 티니핑들 중 한 마리를 수집했습니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                val collectedTiniPing = epicTiniPings.keys.random()
                isTiniPingCollected[collectedTiniPing] = true
                SharedData.updateGridItem(name = collectedTiniPing, isHidden = false)
            }
            else if(tiniPingName == "다조핑") { // 다조핑 버프: 로미가 미수집한 노멀티니핑하나 수집
                popupMessage.text = "$tiniPingName 의 가호!\n노멀 티니핑들 중 한 마리를 수집했습니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                val collectedTiniPing = normalTiniPings.keys.random()
                isTiniPingCollected[collectedTiniPing] = true
                SharedData.updateGridItem(name = collectedTiniPing, isHidden = false)
            }
            else if(tiniPingName == "악동핑") evil = 500
            else if (tiniPingName == "코자핑") { // 코자핑의 가호: 100초 동안 터치 차단
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100초 동안 잠들었습니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                // 코자핑 가호 활성화
                isSleeping = true

                eggImage.setImageResource(R.drawable.romi_blind)
                Handler(Looper.getMainLooper()).postDelayed({
                    isSleeping = false // 100초 후 터치 가능
                    eggImage.setImageResource(R.drawable.romi)
                }, 10_000) // 100초 (100,000ms)

            }
            else if((tiniPingName == "차캐핑" || tiniPingName == "베베핑") &&   twice == 0) { // 차캐&베베핑 버프: 로미 100회동안 터치카운트 + 2
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 2배의 점수를 적용합니다"
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                twice = 10

            }
            else if(tiniPingName == "바네핑" &&   bane == 0) { // 덜덜핑 버프: 로미 100회동안 덜덜더럳ㄹ
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 5배의 점수를 적용합니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                bane = 10
            }
            else if(tiniPingName == "공쥬핑" &&   enlarge == 0) { // 덜덜핑 버프: 로미 100회동안 덜덜더럳ㄹ
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 크기가 증가합니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                enlarge = 10
            }
            else if(tiniPingName == "아휴핑" &&   hu == 0) { // 덜덜핑 버프: 로미 100회동안 덜덜더럳ㄹ
                popupMessage.text = "$tiniPingName 의 가호!\n로미가 100회 동안 50% 확률로 \n점수가 올라가지 않습니다."
                popupMessage.visibility = View.VISIBLE
                zinPopupMessage(popupMessage, tiniPingName, tiniPingRank)
                hu = 10
            }
        }
    }

    private fun zinPopupMessage(popupMessage: TextView, tiniPingName: String, tiniPingRank: String) {
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
                }, 3500) // 3.5초 후에 사라짐
            }
        .start()
    }

    private fun updateIceOverlay(iceOverlay: ImageView) {
        if (frozen > 0) {
            iceOverlay.visibility = View.VISIBLE
        } else {
            iceOverlay.visibility = View.GONE
        }
    }

    private var shakeAnimator: ObjectAnimator? = null

    private fun moveEggRandomly(eggImage: ImageView, containerWidth: Int, containerHeight: Int) {
        // 이미지뷰가 컨테이너를 벗어나지 않도록 조정
        val randomX = Random.nextInt(0, containerWidth - eggImage.width) // X 좌표
        val randomY = Random.nextInt(0, containerHeight - eggImage.height) // Y 좌표

        shakeAnimator?.cancel()

        if(spark == 0 && heavy == 0) {
            // 애니메이션 효과로 위치 이동
            eggImage.animate()
                .x(randomX.toFloat())
                .y(randomY.toFloat())
                .setDuration(300) // 이동 시간 (밀리초)
                .withEndAction {
                    // 이동 후 새로운 떨림 애니메이션 시작
                    if(cold > 0) startShaking(eggImage)
                }
                .start()
        }
        else {
            if(spark > heavy) {
                spark--
                // 애니메이션 효과로 위치 이동
                eggImage.animate()
                    .x(randomX.toFloat())
                    .y(randomY.toFloat())
                    .setDuration(50) // 이동 시간 (밀리초)
                    .withEndAction {
                        // 이동 후 새로운 떨림 애니메이션 시작
                        if(cold > 0) startShaking(eggImage)
                    }
                    .start()
            }
            else {
                heavy--
                // 애니메이션 효과로 위치 이동
                eggImage.animate()
                    .x(randomX.toFloat())
                    .y(randomY.toFloat())
                    .setDuration(600) // 이동 시간 (밀리초)
                    .withEndAction {
                        // 이동 후 새로운 떨림 애니메이션 시작
                        if(cold > 0) startShaking(eggImage)
                    }
                    .start()
            }
        }
    }

    private fun startShaking(eggImage: View) {
        // 기존 애니메이터가 있으면 취소
        shakeAnimator?.cancel()

        // 현재 위치를 기준으로 진폭 계산
        val currentX = eggImage.translationX
        val currentY = eggImage.translationY

        val shakeX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, currentX - 10f, currentX + 10f, currentX)
        val shakeY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, currentY - 5f, currentY + 5f, currentY)

        shakeAnimator = ObjectAnimator.ofPropertyValuesHolder(eggImage, shakeX, shakeY).apply {
            duration = 200 // 떨림 주기: 0.2초
            repeatMode = ValueAnimator.REVERSE // 떨림 방향 반복
            repeatCount = ValueAnimator.INFINITE // 무한 반복
        }
        shakeAnimator?.start()
    }
    private fun stopShaking() {
        shakeAnimator?.cancel() // 떨림 애니메이션 종료
        shakeAnimator = null
    }

    private fun addTouchEffect(view: View) {
        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 터치 시 축소 애니메이션 효과
                    val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                        v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 0.9f),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.9f)
                    )
                    scaleDown.duration = 100
                    scaleDown.start()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // 터치 끝나면 원래 크기로 복원
                    val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                        v,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1f),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f)
                    )
                    scaleUp.duration = 100
                    scaleUp.start()
                }
            }
            // 이벤트를 계속 전달
            false
        }
    }
    private fun showHeart(eggImage: ImageView, x: Float, y: Float) {
        val context = eggImage.context
        val heart = ImageView(context).apply {
            setImageDrawable(ContextCompat.getDrawable(context, R.drawable.heartt)) // 하트 이미지 설정
            layoutParams = ViewGroup.LayoutParams(100, 100) // 하트 크기 설정
            this.x = x - 50 // 중심 맞춤
            this.y = y - 50
        }

        // 부모 뷰 그룹에 추가
        val parentViewGroup = eggImage.parent as? ViewGroup
        parentViewGroup?.addView(heart)

        // 애니메이션: 위로 이동 + 희미해짐
        heart.animate()
            .translationYBy(-200f) // 위로 200px 이동
            .alpha(0f) // 투명해짐
            .setDuration(1000) // 1초 동안 애니메이션
            .withEndAction { parentViewGroup?.removeView(heart) } // 애니메이션 종료 후 제거
            .start()
    }
    fun getRandomKeyWithTrueValue(map: Map<String, Boolean>): String? {
        val trueKeys = map.filter { it.value }.keys.toList()
        return if (trueKeys.isNotEmpty()) {
            trueKeys[Random.nextInt(trueKeys.size)]
        } else {
            null // True인 값이 없을 경우 null 반환
        }
    }
}