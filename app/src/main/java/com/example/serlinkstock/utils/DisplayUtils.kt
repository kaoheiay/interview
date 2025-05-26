package com.example.serlinkstock.utils

import android.content.Context
import android.graphics.Insets
import android.graphics.Rect
import android.os.Build
import android.util.DisplayMetrics
import android.util.Size
import android.view.WindowInsets
import android.view.WindowManager


object DisplayUtils {
    fun getScreenWidth(ctx: Context): Int {
        val wm = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            windowMetrics.bounds.width()
        } else {
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    /**
     * @return 扣除 statusBars、navigationBars 後的高度
     */
    fun getScreenHeight(ctx: Context, deductInset: Boolean = true): Int {
        val wm = ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            // Gets all excluding insets
            val windowInsets = windowMetrics.windowInsets
            val insets: Insets = windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars()
                        or WindowInsets.Type.displayCutout()
                        or WindowInsets.Type.statusBars()
            )

            val insetsWidth: Int = if (deductInset) insets.right + insets.left else 0
            val insetsHeight: Int = if (deductInset) insets.top + insets.bottom else 0
            // Legacy size that Display#getSize reports
            val bounds: Rect = windowMetrics.bounds
            val legacySize = Size(
                bounds.width() - insetsWidth,
                bounds.height() - insetsHeight
            )
            legacySize.height
        } else {
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }

    /**
     * float 类型的 4.1 和4.9 强转成int类型后，会失去精准度变成 int类型的4， 而如果咱们想四舍五入的话，把他们都加上0.5f，这样转换出来的结果就是：
     * 4.4 + 0.5 = 4.9 转为int 还是4，而4.5 + 0.5 = 5.0 转换成int后就是5，正好是四舍五入，这样就保证了咱们算出来的值相对精准。
     */
    fun dp2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }


}