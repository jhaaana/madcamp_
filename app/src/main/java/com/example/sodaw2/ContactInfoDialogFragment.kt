package com.example.sodaw2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import android.view.Gravity
import android.util.TypedValue
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class ContactInfoDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_IMAGE_RES = "imageRes"
        private const val ARG_HP = "hp"
        private const val ARG_DIALOGUE = "dialogue"
        private const val ARG_YOUTUBE = "youtube"

        fun newInstance(name: String, imageRes: Int, hp: String, dialogue: String, youtube: String): ContactInfoDialogFragment {
            val args = Bundle().apply {
                putString(ARG_NAME, name)
                putInt(ARG_IMAGE_RES, imageRes)
                putString(ARG_HP, hp)
                putString(ARG_DIALOGUE, dialogue)
                putString(ARG_YOUTUBE, youtube)
            }
            val fragment = ContactInfoDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_contact_info, container, false)
        val closeButton: ImageView = view.findViewById(R.id.closeButton)

        // 닫기 버튼 동작
        closeButton.setOnClickListener {
            dismiss() // 팝업 창 닫기
        }

        val name = arguments?.getString(ARG_NAME)
        val imageRes = arguments?.getInt(ARG_IMAGE_RES)
        val hp = arguments?.getString(ARG_HP)
        val dialogue = arguments?.getString(ARG_DIALOGUE)
        val youtube = arguments?.getString(ARG_YOUTUBE)

        val imageView: ImageView = view.findViewById(R.id.contactImageView)
        val nameTextView: TextView = view.findViewById(R.id.contactNameTextView)
        val hpTextView: TextView = view.findViewById(R.id.contactHpTextView)
        val dialogueTextView: TextView = view.findViewById(R.id.contactDialogueTextView)
        val youtubePlayerView: YouTubePlayerView = view.findViewById(R.id.youtubePlayerView)

        nameTextView.text = name

        hpTextView.text = "H.P.: $hp"
        dialogueTextView.text = "\"$dialogue\""

        youtube?.let {
            val videoId = it.extractYouTubeVideoId()
            lifecycle.addObserver(youtubePlayerView) // YouTubePlayerView 생명주기 연결

            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(videoId, 0f) // 동영상 미리보기
                }
            })
        }

        imageRes?.let { imageView.setImageResource(it) }

        return view
    }

    private fun String.extractYouTubeVideoId(): String {
        val regex = "(?<=v=|\\/)([\\w-]{11})".toRegex()
        return regex.find(this)?.value ?: this
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout((resources.displayMetrics.widthPixels * 0.9).toInt(), (resources.displayMetrics.heightPixels * 0.55).toInt())
            setGravity(Gravity.CENTER) // 팝업 창을 화면 중앙에 표시
            setDimAmount(0.5f) // 배경 흐림 효과

            // dp를 px로 변환하여 y축 위치 설정

            // 배경을 투명하게 설정
            setBackgroundDrawableResource(android.R.color.transparent)

            val params = attributes
            val dpAsPixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10f, // 원하는 패딩(dp)
                context?.resources?.displayMetrics
            ).toInt()
            params.y = dpAsPixels
            attributes = params
        }
    }
}
