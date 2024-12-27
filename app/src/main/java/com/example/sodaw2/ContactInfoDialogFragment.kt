package com.example.sodaw2

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import android.view.Gravity
import android.util.TypedValue

class ContactInfoDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_IMAGE_RES = "imageRes"

        fun newInstance(name: String, imageRes: Int): ContactInfoDialogFragment {
            val args = Bundle().apply {
                putString(ARG_NAME, name)
                putInt(ARG_IMAGE_RES, imageRes)
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

        val name = arguments?.getString(ARG_NAME)
        val imageRes = arguments?.getInt(ARG_IMAGE_RES)

        val imageView: ImageView = view.findViewById(R.id.contactImageView)
        val nameTextView: TextView = view.findViewById(R.id.contactNameTextView)

        nameTextView.text = name
        imageRes?.let { imageView.setImageResource(it) }

        return view
    }

    /*override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }*/
    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setGravity(Gravity.TOP) // 팝업 창을 화면 상단에 표시
            setDimAmount(0.5f) // 배경 흐림 효과

            // dp를 px로 변환하여 y축 위치 설정
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
