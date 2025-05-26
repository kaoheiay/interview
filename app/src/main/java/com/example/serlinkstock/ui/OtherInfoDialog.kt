package com.example.serlinkstock.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.asiainnovations.ace.dp2px
import com.asiainnovations.ace.getScreenWidth
import com.example.serlinkstock.R
import com.example.serlinkstock.databinding.DialogOtherInfoBinding
import com.example.serlinkstock.model.CombinedItem

//import com.uuzuche.lib_zxing.activity.CodeUtils


class OtherInfoDialog : DialogFragment() {
    private lateinit var binding: DialogOtherInfoBinding

    val combinedItem by lazy {
        arguments?.get(CombinedItem.NAME) as CombinedItem?
    }

    private fun setWindowFrame() {
        val window = dialog?.window!!
        // 一定要设置Background，如果不设置，window属性设置无效
        window.setBackgroundDrawableResource(R.drawable.bg_solid_bg01_radius_8)
        window.decorView.setPadding(0, 0, 0, 0)

        val dm = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(dm)

        val params = window.attributes
        params.gravity = Gravity.CENTER
        params.width = requireActivity().getScreenWidth() - 100f.dp2px(binding.root.context)
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        window.attributes = params
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogOtherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWindowFrame()
        combinedItem?.apply {
            binding.tvInfo.text = stockName
            binding.tvPERatio.text = peRatio
            binding.tvDividendYield.text = dividendYield
            binding.tvPBRatio.text = pbRatio
        }
    }
}