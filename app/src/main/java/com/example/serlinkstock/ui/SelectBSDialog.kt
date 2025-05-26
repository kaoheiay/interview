package com.example.serlinkstock.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.asiainnovations.ace.dp2px
import com.example.serlinkstock.databinding.DialogSelectBsBinding
import com.example.serlinkstock.utils.DisplayUtils
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectBSDialog(val isAscending: Boolean) : BottomSheetDialogFragment() {
    lateinit var binding: DialogSelectBsBinding

    companion object {
        const val TAG = "CommonSelectBSDialog"
    }

    var clickDescending: (() -> Unit)? = null
    fun setOnClickDescendingListener(clickDescending: () -> Unit) {
        this.clickDescending = clickDescending
    }

    var clickAscending: (() -> Unit)? = null
    fun setOnClickAscendingListener(clickAscending: () -> Unit) {
        this.clickAscending = clickAscending
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSelectBsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //參考 https://www.gushiciku.cn/pl/pjwY/zh-tw
        dialog?.let {
            val bottomSheet: FrameLayout = it.findViewById(R.id.design_bottom_sheet)
            //獲取behavior
            val behavior = BottomSheetBehavior.from(bottomSheet)
            //背景需設為透明，圓角設定才會有效果
            //bottomSheet.background = AppCompatResources.getDrawable(it.context, R.drawable.transparent)
            bottomSheet.setBackgroundColor(Color.TRANSPARENT)
            //設定view高度
            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            //view最高高度
            behavior.maxHeight = DisplayUtils.getScreenHeight(it.context) - 44f.dp2px(it.context)
            //設定彈出高度, 此方式造成需下滑很多時才能關閉Dialog。
            //behavior.peekHeight = 3000
            //略過折疊狀態, True
            behavior.skipCollapsed = true
            //設定展開狀態
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.clAscending.isSelected = isAscending
        binding.clDescending.isSelected = !isAscending

        binding.clDescending.setOnClickListener {
            clickDescending?.invoke()
            dismiss()
        }

        binding.clAscending.setOnClickListener {
            clickAscending?.invoke()
            dismiss()
        }
    }
}
